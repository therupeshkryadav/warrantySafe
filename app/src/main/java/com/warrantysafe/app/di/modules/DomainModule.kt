package com.warrantysafe.app.di.modules

import com.warrantysafe.app.domain.useCases.AddNotificationUseCase
import com.warrantysafe.app.domain.useCases.AddProductUseCase
import com.warrantysafe.app.domain.useCases.CheckUserUseCase
import com.warrantysafe.app.domain.useCases.CheckUsernameUseCase
import com.warrantysafe.app.domain.useCases.DeleteProductsUseCase
import com.warrantysafe.app.domain.useCases.GetActiveProductsUseCase
import com.warrantysafe.app.domain.useCases.GetAllProductsUseCase
import com.warrantysafe.app.domain.useCases.GetBottomNavigationIconsUseCase
import com.warrantysafe.app.domain.useCases.GetExpiredProductsUseCase
import com.warrantysafe.app.domain.useCases.GetNotificationsUseCase
import com.warrantysafe.app.domain.useCases.GetProductDetailUseCase
import com.warrantysafe.app.domain.useCases.GetUpcomingUseCase
import com.warrantysafe.app.domain.useCases.GetUserUseCase
import com.warrantysafe.app.domain.useCases.LoginUserUseCase
import com.warrantysafe.app.domain.useCases.MarkNotificationReadUseCase
import com.warrantysafe.app.domain.useCases.SearchProductsUseCase
import com.warrantysafe.app.domain.useCases.SignOutUserUseCase
import com.warrantysafe.app.domain.useCases.SignUpUserUseCase
import com.warrantysafe.app.domain.useCases.UpdateProductUseCase
import com.warrantysafe.app.domain.useCases.UpdateUserUseCase
import org.koin.dsl.module

val domainModule = module {
    // Product Use Cases
    single { GetProductDetailUseCase(get()) }
    single { GetAllProductsUseCase(get()) }
    single { SearchProductsUseCase(get()) }
    single { GetActiveProductsUseCase(get()) }
    single { GetExpiredProductsUseCase(get()) }
    single { UpdateProductUseCase(get()) }
    single { DeleteProductsUseCase(get()) }
    single { AddProductUseCase(get()) }

    // User Use Cases
    single { SignUpUserUseCase(get()) }
    single { CheckUserUseCase(get()) }
    single { CheckUsernameUseCase(get()) }
    single { LoginUserUseCase(get()) }
    single { GetUserUseCase(get()) }
    single { UpdateUserUseCase(get()) }
    single { SignOutUserUseCase(get()) }

    // Miscellaneous Use Cases
    single { AddNotificationUseCase(get()) }
    single { GetNotificationsUseCase(get()) }
    single { MarkNotificationReadUseCase(get()) }
    single { GetBottomNavigationIconsUseCase(get()) }
    single { GetUpcomingUseCase(get()) }
}

