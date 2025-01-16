package com.warrantysafe.app.di.modules

import com.warrantysafe.app.data.api.AppwriteClient
import com.warrantysafe.app.data.repository.AuthRepositoryImpl
import com.warrantysafe.app.data.repository.BottomNavigationRepositoryImpl
import com.warrantysafe.app.data.repository.NotificationRepositoryImpl
import com.warrantysafe.app.data.repository.ProductRepositoryImpl
import com.warrantysafe.app.data.repository.RecentRepositoryImpl
import com.warrantysafe.app.data.repository.SettingsRepositoryImpl
import com.warrantysafe.app.data.repository.UpcomingRepositoryImpl
import com.warrantysafe.app.data.repository.UserRepositoryImpl
import com.warrantysafe.app.domain.repository.AuthRepository
import com.warrantysafe.app.domain.repository.BottomNavigationRepository
import com.warrantysafe.app.domain.repository.NotificationRepository
import com.warrantysafe.app.domain.repository.ProductRepository
import com.warrantysafe.app.domain.repository.RecentRepository
import com.warrantysafe.app.domain.repository.SettingsRepository
import com.warrantysafe.app.domain.repository.UpcomingRepository
import com.warrantysafe.app.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    // AppwriteClient
    single { AppwriteClient.createClient(androidContext()) }  // Network client (Appwrite)
    single<ProductRepository> { ProductRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }// Add data layer dependencies here, e.g., repositories, network clients
    single<NotificationRepository> { NotificationRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
    single<UserRepository> { UserRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
    single<RecentRepository> { RecentRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
    single<BottomNavigationRepository> { BottomNavigationRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
    single<UpcomingRepository> { UpcomingRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
    single<SettingsRepository> { SettingsRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
}
