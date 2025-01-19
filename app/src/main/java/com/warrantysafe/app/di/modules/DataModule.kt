package com.warrantysafe.app.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.warrantysafe.app.data.repository.BottomNavigationRepositoryImpl
import com.warrantysafe.app.data.repository.NotificationRepositoryImpl
import com.warrantysafe.app.data.repository.ProductRepositoryImpl
import com.warrantysafe.app.data.repository.RecentRepositoryImpl
import com.warrantysafe.app.data.repository.SettingsRepositoryImpl
import com.warrantysafe.app.data.repository.UpcomingRepositoryImpl
import com.warrantysafe.app.data.repository.UserRepositoryImpl
import com.warrantysafe.app.domain.repository.BottomNavigationRepository
import com.warrantysafe.app.domain.repository.NotificationRepository
import com.warrantysafe.app.domain.repository.ProductRepository
import com.warrantysafe.app.domain.repository.RecentRepository
import com.warrantysafe.app.domain.repository.SettingsRepository
import com.warrantysafe.app.domain.repository.UpcomingRepository
import com.warrantysafe.app.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    // Provide FirebaseAuth instance
    single { FirebaseAuth.getInstance() }
    // Provide FirebaseFirestore instance
    single { FirebaseFirestore.getInstance() }
    single<ProductRepository> { ProductRepositoryImpl(get(), get()) } // Add data layer dependencies here, e.g., repositories, network clients
    single<NotificationRepository> { NotificationRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
    single<UserRepository> { UserRepositoryImpl(get(), get()) } // Add data layer dependencies here, e.g., repositories, network clients
    single<RecentRepository> { RecentRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
    single<BottomNavigationRepository> { BottomNavigationRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
    single<UpcomingRepository> { UpcomingRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
    single<SettingsRepository> { SettingsRepositoryImpl() } // Add data layer dependencies here, e.g., repositories, network clients
}
