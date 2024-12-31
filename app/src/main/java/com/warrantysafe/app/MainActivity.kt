package com.warrantysafe.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.warrantysafe.app.presentation.ui.navgraph.NavGraph
import com.warrantysafe.app.ui.theme.WarrantySafeTheme

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
