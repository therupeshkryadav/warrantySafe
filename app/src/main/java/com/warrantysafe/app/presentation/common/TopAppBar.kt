package com.warrantysafe.app.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.common.components.actions
import com.warrantysafe.app.presentation.common.components.navigationIcons
import com.warrantysafe.app.presentation.common.components.textCompose

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable() (RowScope.() -> Unit) = {},
    backgroundColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.surface,
    titleColor: androidx.compose.ui.graphics.Color = colorResource(R.color.black)
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CenterAlignedTopAppBar(
            title = title,
            navigationIcon = navigationIcon,
            actions = actions,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = backgroundColor, // Background color
                titleContentColor = titleColor // Title color
            )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(color = colorResource(R.color.black))
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TopAppBarProfilePreview(
) {

    TopAppBar(
        title = {
            textCompose(
                isSearch = false,
                isAddWarranty = true,
                isHomeorProfile = false
            )
        },
        navigationIcon = {
            navigationIcons(
            isSearch = false,
            isAddWarranty = true,
            isHomeorProfile = false
        ) },
        actions = {
            actions(
                isSearch = false,
                isAddWarranty = true,
                isHomeorProfile = false
            )
        }
    )
}