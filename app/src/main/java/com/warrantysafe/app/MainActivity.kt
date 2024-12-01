package com.warrantysafe.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.presentation.navgraph.NavGraph
import com.warrantysafe.app.ui.theme.WarrantySafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WarrantySafeTheme {
                MainActivityContent()
            }
        }
    }
}

@Composable
fun MainActivityContent() {
    NavGraph()
}

@Preview
@Composable
fun PreviewMain(){
    MainActivity()
}




