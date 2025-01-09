package com.warrantysafe.app.domain.model

import androidx.annotation.DrawableRes
import com.warrantysafe.app.presentation.navigation.Route

data class BottomNavigation(
    @DrawableRes val icon: Int,
    val text: String,
    val route: Route
)