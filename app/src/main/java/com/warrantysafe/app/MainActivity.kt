package com.warrantysafe.app

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.warrantysafe.app.di.appModule
import com.warrantysafe.app.di.dataModule
import com.warrantysafe.app.di.domainModule
import com.warrantysafe.app.di.presentationModule
import com.warrantysafe.app.presentation.ui.navgraph.NavGraph
import com.warrantysafe.app.ui.theme.WarrantySafeTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WarrantySafeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidContext(this@WarrantySafeApplication)
            modules(listOf(appModule, dataModule, domainModule, presentationModule))
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarrantySafeTheme {
                MainActivityContent()  // Your Composable Content
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
