package com.warrantysafe.app.di.modules

import com.warrantysafe.app.data.repository.NotificationRepositoryImpl
import com.warrantysafe.app.data.repository.ProductRepositoryImpl
import com.warrantysafe.app.domain.repository.NotificationRepository
import com.warrantysafe.app.domain.repository.ProductRepository
import org.koin.dsl.module

val dataModule = module {
    // Provide the GreetingRepository implementation
    single<ProductRepository> { ProductRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
    single<NotificationRepository> { NotificationRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
}
