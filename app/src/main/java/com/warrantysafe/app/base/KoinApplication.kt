package com.warrantysafe.app.base

import android.app.Application
import com.warrantysafe.app.di.appModule
import com.warrantysafe.app.di.dataModule
import com.warrantysafe.app.di.domainModule
import com.warrantysafe.app.di.presentationModule
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