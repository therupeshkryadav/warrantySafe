package com.warrantysafe.app.data.repository

import android.content.ContentResolver
import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.repository.UserRepository
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.presentation.navigation.Route
import io.appwrite.Client
import io.appwrite.ID
import io.appwrite.models.InputFile
import io.appwrite.services.Storage
import kotlinx.coroutines.tasks.await
import java.io.File

class UserRepositoryImpl(
    private val context: Context,
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val appwriteClient: Client,
    private val appwriteStorage: Storage
) : UserRepository {

    private val usersCollection = firestore.collection("users")

    override suspend fun checkUser(): String {
        return if (firebaseAuth.currentUser != null)
            Route.HomeScreen.route
        else
            Route.LoginScreen.route
    }

    override suspend fun signUpUser(user: User): Results<User> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                // Upload profile image to Appwrite (if provided)
                val profileImageUri = user.profileImageUrl
                val uploadedProfileImageUrl = if (profileImageUri.isNotEmpty()) {
                    uploadProfileImageToAppwrite(profileImageUri)
                } else {
                    ""
                }

                // Save user data to Firestore
                val userId = firebaseUser.uid
                val userData = mapOf(
                    "username" to user.username,
                    "name" to user.name,
                    "email" to user.email,
                    "phoneNumber" to user.phoneNumber,
                    "profileImageUrl" to uploadedProfileImageUrl
                )
                usersCollection.document(userId).set(userData).await()

                Results.Success(user.copy(profileImageUrl = uploadedProfileImageUrl))
            } else {
                Results.Failure(Exception("User creation failed"))
            }
        } catch (e: Exception) {
            Results.Failure(e)
        }
    }

    override suspend fun loginUser(email: String, password: String): Results<User> {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user

            if (firebaseUser != null) {
                val userDocument = usersCollection.document(firebaseUser.uid).get().await()

                if (userDocument.exists()) {
                    val user = User(
                        name = userDocument.getString("name") ?: "",
                        email = userDocument.getString("email") ?: "",
                        username = userDocument.getString("username") ?: "",
                        phoneNumber = userDocument.getString("phoneNumber") ?: "",
                        profileImageUrl = userDocument.getString("profileImageUrl") ?: ""
                    )
                    Results.Success(user)
                } else {
                    Results.Failure(Exception("User data not found in Firestore"))
                }
            } else {
                Results.Failure(Exception("Authentication failed: Firebase user is null"))
            }
        } catch (e: Exception) {
            Results.Failure(e)
        }
    }

    override suspend fun getUser(): Results<User> {
        return try {
            val firebaseUser = firebaseAuth.currentUser

            if (firebaseUser != null) {
                val userDocument = usersCollection.document(firebaseUser.uid).get().await()

                if (userDocument.exists()) {
                    val user = User(
                        name = userDocument.getString("name") ?: "",
                        email = userDocument.getString("email") ?: "",
                        username = userDocument.getString("username") ?: "",
                        phoneNumber = userDocument.getString("phoneNumber") ?: "",
                        profileImageUrl = userDocument.getString("profileImageUrl") ?: ""
                    )
                    Results.Success(user)
                } else {
                    Results.Failure(Exception("User data not found in Firestore"))
                }
            } else {
                Results.Failure(Exception("No authenticated user found"))
            }
        } catch (e: Exception) {
            Results.Failure(e)
        }
    }

    override suspend fun updateUser(user: User): Results<User> {
        return try {
            val userId = firebaseAuth.currentUser?.uid
                ?: return Results.Failure(Exception("No authenticated user found"))
            Log.d("AppWriteUpload", "upload profile image: ${user.profileImageUrl}")
            // Upload profile image to Appwrite (if provided)
            val updatedProfileImageUrl = if (user.profileImageUrl.isNotEmpty()) {
                try {
                    uploadProfileImageToAppwrite(user.profileImageUrl)
                } catch (e: Exception) {
                    Log.e("AppWriteUpload", "Failed to upload profile image: ${e.message}")
                    return Results.Failure(e)
                }
            } else {
                ""
            }

            // Prepare user data for update
            val userData = mapOf(
                "username" to user.username,
                "name" to user.name,
                "email" to user.email,
                "phoneNumber" to user.phoneNumber,
                "profileImageUrl" to updatedProfileImageUrl
            )

            // Update Firestore database
            usersCollection.document(userId).update(userData).await()

            Results.Success(user.copy(profileImageUrl = updatedProfileImageUrl))
        } catch (e: Exception) {
            Log.e("UpdateUser", "Error updating user: ${e.message}")
            Results.Failure(e)
        }
    }

    override suspend fun signOutUser(): Results<Unit> {
        return try {
            firebaseAuth.signOut()
            Results.Success(Unit)
        } catch (e: Exception) {
            Results.Failure(e)
        }
    }

    /**
     * Helper function to upload a profile image to Appwrite storage
     */
    private suspend fun uploadProfileImageToAppwrite(uri: String): String {
        return try {
            // Resolve the content:// URI using ContentResolver
            val contentResolver: ContentResolver = context.contentResolver
            val tempFile = File.createTempFile("profile_image", ".jpg", context.cacheDir)

            // Open the input stream and copy to the temporary file
            contentResolver.openInputStream(android.net.Uri.parse(uri)).use { inputStream ->
                tempFile.outputStream().use { outputStream ->
                    inputStream?.copyTo(outputStream)
                }
            }

            // Upload the temporary file to Appwrite storage
            val file = appwriteStorage.createFile(
                bucketId = "678fd444002f3d3c4897",
                fileId = ID.unique(),
                file = InputFile.fromFile(tempFile)
            )

            // Return the file's public URL
            "${appwriteClient.endPoint}/storage/buckets/${file.bucketId}/files/${file.id}/view?project=warranty-safe&project=warranty-safe&mode=admin"
        } catch (e: Exception) {
            Log.e("AppwriteUpload", "Error uploading profile image", e)
            ""
        }
    }
}
