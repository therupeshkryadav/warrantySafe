package com.warrantysafe.app.data.api

import android.content.Context
import io.appwrite.Client

object AppwriteClient {
    fun createClient(context: Context): Client {
        return Client(context).setEndpoint("").setProject("")
    }
}
