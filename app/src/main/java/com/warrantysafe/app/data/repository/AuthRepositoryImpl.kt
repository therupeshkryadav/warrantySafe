package com.warrantysafe.app.data.repository

import android.net.Uri
import com.warrantysafe.app.domain.repository.AuthRepository
import io.appwrite.Client
import io.appwrite.exceptions.AppwriteException
import io.appwrite.services.Account
import io.appwrite.services.Databases

class AuthRepositoryImpl(
    private val appwriteClient: Client,
    private val databaseId: String,
    private val usersCollectionId: String
) : AuthRepository {

    private val account = Account(appwriteClient)
    private val databases = Databases(appwriteClient)

    override suspend fun loginWithUsernameAndPassword(username: String, password: String): Result<String> {
        return try {
            // Search for the user by username or email in the database
            val userQuery = databases.listDocuments(
                databaseId = databaseId,
                collectionId = usersCollectionId,
                queries = listOf(
                    "username=$username" // Filter by username
                )
            )

            if (userQuery.documents.isEmpty()) {
                // If no user found by username, search by email
                val emailQuery = databases.listDocuments(
                    databaseId = databaseId,
                    collectionId = usersCollectionId,
                    queries = listOf(
                        "email=$username" // Treat input as email
                    )
                )

                if (emailQuery.documents.isEmpty()) {
                    Result.failure(Exception("Username or email not found"))
                } else {
                    val email = emailQuery.documents[0].data["email"].toString()
                    // Authenticate using email and password
                    val session = account.createSession(email, password)
                    Result.success(session.userId)
                }
            } else {
                val email = userQuery.documents[0].data["email"].toString()
                // Authenticate using email and password
                val session = account.createSession(email, password)
                Result.success(session.userId)
            }
        } catch (e: AppwriteException) {
            Result.failure(e)
        }
    }

    override suspend fun signupUser(
        imageUri: Uri,
        username: String,
        email: String,
        phoneNo: String,
        password: String
    ): Result<String> {
        return try {
            // Create user account in Appwrite
            val user = account.create(
                userId = "unique()",
                email = email,
                password = password,
                name = username
            )

            // Store additional user data in the database
            databases.createDocument(
                databaseId = databaseId,
                collectionId = usersCollectionId,
                documentId = "unique()",
                data = mapOf(
                    "username" to username,
                    "email" to email,
                    "phoneNo" to phoneNo,
                    "profileImageUri" to imageUri.toString(),
                    "userId" to user.id // Link the document to the user
                )
            )

            Result.success(user.id)
        } catch (e: AppwriteException) {
            Result.failure(e)
        }
    }

    override suspend fun signOut(): Result<Unit> {
        return try {
            account.deleteSession("current")
            Result.success(Unit)
        } catch (e: AppwriteException) {
            Result.failure(e)
        }
    }
}
