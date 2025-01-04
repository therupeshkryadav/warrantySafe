package com.warrantysafe.app.di

import com.warrantysafe.app.di.modules.dataModule
import com.warrantysafe.app.di.modules.domainModule
import com.warrantysafe.app.di.modules.presentationModule
import org.koin.dsl.module

val appModule = module {
    includes(dataModule, domainModule, presentationModule)// Add application-wide dependencies here
}
