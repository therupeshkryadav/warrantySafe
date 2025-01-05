package com.warrantysafe.app.di.modules

import com.warrantysafe.app.domain.useCases.AddProductUseCase
import com.warrantysafe.app.domain.useCases.GetActiveProductsUseCase
import com.warrantysafe.app.domain.useCases.GetExpiredProductsUseCase
import com.warrantysafe.app.domain.useCases.GetNotificationsUseCase
import com.warrantysafe.app.domain.useCases.GetProductsUseCase
import com.warrantysafe.app.domain.useCases.GetUserDetailUseCase
import org.koin.dsl.module

val domainModule = module {
    // Provide Use Cases
    single { GetProductsUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { GetActiveProductsUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { GetExpiredProductsUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { GetUserDetailUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { GetNotificationsUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { AddProductUseCase(get()) }
}
