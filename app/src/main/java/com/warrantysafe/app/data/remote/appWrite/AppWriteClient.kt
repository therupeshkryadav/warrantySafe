package com.warrantysafe.app.data.remote.appWrite

import io.appwrite.Client

object AppwriteClient {
    fun getClient(): Client {
        return Client()
            .setEndpoint("https://cloud.appwrite.io/v1") // Replace with your Appwrite endpoint
            .setProject("") // Replace with your Appwrite project ID
            .setKey("") // Replace with your API key
    }
}
