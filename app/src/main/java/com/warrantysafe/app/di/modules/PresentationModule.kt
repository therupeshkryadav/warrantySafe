package com.warrantysafe.app.di.modules

import com.warrantysafe.app.presentation.viewModel.BottomNavigationViewModel
import com.warrantysafe.app.presentation.viewModel.NotificationViewModel
import com.warrantysafe.app.presentation.viewModel.ProductViewModel
import com.warrantysafe.app.presentation.viewModel.RecentViewModel
import com.warrantysafe.app.presentation.viewModel.SettingsViewModel
import com.warrantysafe.app.presentation.viewModel.UpcomingViewModel
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import com.warrantysafe.app.presentation.viewModel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel { ProductViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { NotificationViewModel(get()) }
    viewModel { UserViewModel(get(),get()) }
    viewModel { RecentViewModel(get()) }
    viewModel { BottomNavigationViewModel(get()) }
    viewModel { UpcomingViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}

