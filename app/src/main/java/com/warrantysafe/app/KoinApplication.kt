package com.warrantysafe.app

import android.app.Application
import com.warrantysafe.app.di.appModule
import com.warrantysafe.app.di.modules.dataModule
import com.warrantysafe.app.di.modules.domainModule
import com.warrantysafe.app.di.modules.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidContext(this@KoinApplication)
            modules(listOf(appModule, dataModule, domainModule, presentationModule))
        }
    }
}