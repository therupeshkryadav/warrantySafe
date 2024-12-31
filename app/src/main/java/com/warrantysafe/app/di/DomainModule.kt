package com.warrantysafe.app.di

import org.koin.dsl.module

val domainModule = module {
    factory { } // Add domain layer dependencies here, e.g., use cases
}
