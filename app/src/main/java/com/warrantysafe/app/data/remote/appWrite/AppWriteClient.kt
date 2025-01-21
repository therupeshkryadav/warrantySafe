package com.warrantysafe.app.data.remote.appWrite

import io.appwrite.Client

object AppwriteClient {
    fun getClient(): Client {
        return Client()
            .setEndpoint("https://cloud.appwrite.io/v1") // Replace with your Appwrite endpoint
            .setProject("warranty-safe") // Replace with your Appwrite project ID
            .setKey("standard_ae9a94cb3207fa8de09fb27ae1a7057c8c449b3d9d4b2810313cdc0848f6a5f4c375ed1e31f790b932d073debf47d92c60936b0d2beaa7f77362fa26f1e7837f4561420e6f4cb1f041812d141be49d5b95d2f644c08344ba657bba0b58358e9ed79bc0708cbeb77ee559b81fe16677489bb71b0909050a1bbd398d43e6387f9c") // Replace with your API key
    }
}