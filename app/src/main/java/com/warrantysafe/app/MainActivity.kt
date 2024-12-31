package com.warrantysafe.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import com.warrantysafe.app.presentation.ui.navgraph.NavGraph
import com.warrantysafe.app.ui.theme.WarrantySafeTheme
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Koin
        startKoin {
            // Android context
            androidContext(this@MainActivity)
            // Load modules
            modules(appModule)
        }
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




