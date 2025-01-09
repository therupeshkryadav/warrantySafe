package com.warrantysafe.app.presentation.ui.screens.utils.customBottomNavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.warrantysafe.app.R
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.viewModel.BottomNavigationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CustomBottomNavigation(
    modifier: Modifier,
    currentRoute: Route,
    onItemClick: (Route) -> Unit
) {
    val bottomNavigationViewModel : BottomNavigationViewModel = koinViewModel()

    LaunchedEffect(Unit){
        bottomNavigationViewModel.loadBottomNavigationIcons()
    }

    val items = bottomNavigationViewModel.bottomNavigation.value

    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEach { item ->
            val isSelected = item.route == currentRoute
            NavigationBarItem(
                selected = isSelected,
                onClick = { if(item.route != currentRoute )onItemClick(item.route) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.text,
                            modifier = Modifier.size(20.dp),
                            tint = if (isSelected) MaterialTheme.colorScheme.primary
                            else colorResource(id = R.color.body)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isSelected) MaterialTheme.colorScheme.primary
                            else colorResource(id = R.color.body)
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.background
                ),
            )
        }
    }
}
