package com.warrantysafe.app.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.navigation.AppNavGraph
import com.warrantysafe.app.presentation.ui.theme.WarrantySafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarrantySafeTheme {
                AppNavGraph(rememberNavController())
            }
        }
    }
}


