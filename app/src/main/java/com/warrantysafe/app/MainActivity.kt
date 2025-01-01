package com.warrantysafe.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.warrantysafe.app.presentation.navigation.NavGraph
import com.warrantysafe.app.ui.theme.WarrantySafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarrantySafeTheme {
                NavGraph()
            }
        }
    }
}


