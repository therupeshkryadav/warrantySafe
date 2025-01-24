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
import io.appwrite.Client
import io.appwrite.services.Storage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    // Provide FirebaseAuth instance
    single { FirebaseAuth.getInstance() }

    // Provide FirebaseFirestore instance
    single { FirebaseFirestore.getInstance() }

    // Provide Appwrite Client
    single {
        Client(androidContext().toString()).apply {
            setEndpoint("https://cloud.appwrite.io/v1") // Replace with your Appwrite endpoint
            setProject("warranty-safe") // Replace with your Appwrite project ID
            setSelfSigned(true) // For local Appwrite installations, allow self-signed certificates
        }
    }

    // Provide Appwrite Storage
    single { Storage(get()) }

    // Provide repositories
    single<ProductRepository> { ProductRepositoryImpl(get(), get(), get(), get(), get()) }
    single<NotificationRepository> { NotificationRepositoryImpl() }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get(), get(), get()) }
    single<RecentRepository> { RecentRepositoryImpl() }
    single<BottomNavigationRepository> { BottomNavigationRepositoryImpl() }
    single<UpcomingRepository> { UpcomingRepositoryImpl() }
    single<SettingsRepository> { SettingsRepositoryImpl() }
}

