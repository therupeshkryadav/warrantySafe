package com.warrantysafe.app.di.modules

import com.warrantysafe.app.presentation.viewModel.BottomNavigationViewModel
import com.warrantysafe.app.presentation.viewModel.NotificationViewModel
import com.warrantysafe.app.presentation.viewModel.ProductViewModel
import com.warrantysafe.app.presentation.viewModel.UpcomingViewModel
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    // Product

    viewModel { ProductViewModel(get(),get(), get(), get(), get(), get(), get(), get()) }

    // User
    viewModel { UserViewModel(get(), get(), get(), get(), get(), get(), get(),get()) }

    // Notifications
    viewModel { NotificationViewModel(get(), get(),get()) }

    // Bottom Navigation
    viewModel { BottomNavigationViewModel(get()) }

    // Upcoming
    viewModel { UpcomingViewModel(get()) }
}


