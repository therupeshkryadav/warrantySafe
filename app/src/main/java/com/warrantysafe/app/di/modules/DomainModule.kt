package com.warrantysafe.app.di.modules

import com.warrantysafe.app.domain.useCases.AddProductUseCase
import com.warrantysafe.app.domain.useCases.GetProductsUseCase
import org.koin.dsl.module

val domainModule = module {
    // Provide Use Cases
    single { GetProductsUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { AddProductUseCase(get()) }
}
