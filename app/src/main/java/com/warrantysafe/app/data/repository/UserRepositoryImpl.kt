package com.warrantysafe.app.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.warrantysafe.app.domain.model.User
import com.warrantysafe.app.domain.repository.UserRepository
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : UserRepository {

    private val usersCollection = firestore.collection("users")

    override suspend fun signUpUser(user: User): Result<User> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                // Save user data to Firestore
                val userId = firebaseUser.uid
                val userData = mapOf(
                    "username" to user.username,
                    "name" to user.name,
                    "email" to user.email,
                    "phoneNumber" to user.phoneNumber,
                    "profileImageUri" to user.profileImageUri
                )
                usersCollection.document(userId).set(userData).await()
                Result.success(user.copy(username = user.username))
            } else {
                Result.failure(Exception("User creation failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun loginUser(email: String, password: String): Result<User> {
        return try {
            // Sign in the user using Firebase Authentication
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user

            if (firebaseUser != null) {
                // Fetch additional user data from Firestore
                val userDocument = firestore.collection("users").document(firebaseUser.uid).get().await()

                if (userDocument.exists()) {
                    // Map Firestore document to the User object
                    val user = User(
                        name = userDocument.getString("name") ?: "",
                        email = userDocument.getString("email") ?: "",
                        username = userDocument.getString("username") ?: "" ,
                        phoneNumber = userDocument.getString("phoneNumber") ?: "" ,
                        profileImageUri = userDocument.getString("profileImageUri") ?: ""// Example additional field
                    )
                    Result.success(user)
                } else {
                    Result.failure(Exception("User data not found in Firestore"))
                }
            } else {
                Result.failure(Exception("Authentication failed: Firebase user is null"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateUser(user: User): Result<Unit> {
        return try {
            val userId = firebaseAuth.currentUser?.uid ?: throw Exception("User not logged in")
            usersCollection.document(userId).set(user).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signOutUser(): Result<Unit> {
        return try {
            firebaseAuth.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
