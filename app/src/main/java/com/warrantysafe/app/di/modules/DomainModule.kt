package com.warrantysafe.app.di.modules

import com.warrantysafe.app.domain.useCases.AddProductUseCase
import com.warrantysafe.app.domain.useCases.CheckUserUseCase
import com.warrantysafe.app.domain.useCases.DeleteProductsUseCase
import com.warrantysafe.app.domain.useCases.GetActiveProductsUseCase
import com.warrantysafe.app.domain.useCases.GetAllProductsUseCase
import com.warrantysafe.app.domain.useCases.GetBottomNavigationIconsUseCase
import com.warrantysafe.app.domain.useCases.GetExpiredProductsUseCase
import com.warrantysafe.app.domain.useCases.GetNotificationsUseCase
import com.warrantysafe.app.domain.useCases.GetRecentsUseCase
import com.warrantysafe.app.domain.useCases.GetSettingsUseCase
import com.warrantysafe.app.domain.useCases.GetUpcomingUseCase
import com.warrantysafe.app.domain.useCases.GetUserUseCase
import com.warrantysafe.app.domain.useCases.LoginUserUseCase
import com.warrantysafe.app.domain.useCases.SignOutUserUseCase
import com.warrantysafe.app.domain.useCases.SignUpUserUseCase
import com.warrantysafe.app.domain.useCases.UpdateProductUseCase
import com.warrantysafe.app.domain.useCases.UpdateUserUseCase
import org.koin.dsl.module

val domainModule = module {
    // Provide Use Cases
    single { GetAllProductsUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { GetActiveProductsUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { GetExpiredProductsUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { UpdateProductUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { DeleteProductsUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { SignUpUserUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { CheckUserUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { LoginUserUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { GetUserUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { UpdateUserUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { SignOutUserUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { GetNotificationsUseCase(get()) } // Add domain layer dependencies here, e.g., use cases
    single { AddProductUseCase(get()) }
    single { GetRecentsUseCase(get()) }
    single { GetBottomNavigationIconsUseCase(get()) }
    single { GetUpcomingUseCase(get()) }
    single { GetSettingsUseCase(get()) }
}
