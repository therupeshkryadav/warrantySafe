package com.warrantysafe.app.di.modules


import com.warrantysafe.app.presentation.viewModel.NotificationViewModel
import com.warrantysafe.app.presentation.viewModel.ProductViewModel
import com.warrantysafe.app.presentation.viewModel.RecentViewModel
import com.warrantysafe.app.presentation.viewModel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { ProductViewModel(get(), get(), get(), get()) }
    viewModel { NotificationViewModel(get()) }
    viewModel { UserViewModel(get()) }
    viewModel { RecentViewModel(get()) }
}

