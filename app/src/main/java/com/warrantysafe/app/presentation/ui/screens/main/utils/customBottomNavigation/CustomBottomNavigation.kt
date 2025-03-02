import android.view.WindowInsets
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.presentation.navigation.Route
import com.warrantysafe.app.presentation.viewModel.BottomNavigationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CustomBottomNavigation(
    modifier: Modifier = Modifier,
    currentRoute: Route,
    onItemClick: (Route) -> Unit
) {
    val bottomNavigationViewModel: BottomNavigationViewModel = koinViewModel()
    LaunchedEffect(Unit) {
        bottomNavigationViewModel.loadBottomNavigationIcons()
    }
    val items = bottomNavigationViewModel.bottomNavigation.value

    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(24.dp)).border(1.dp,Color.DarkGray, RoundedCornerShape(24.dp)),
        containerColor = Color.White,
        tonalElevation = 10.dp
    ) {
        items.forEach { item ->
            val isSelected = item.route == currentRoute
            val scale by animateFloatAsState(if (isSelected) 1.2f else 1f, label = "Icon Scale")

            NavigationBarItem(
                selected = isSelected,
                onClick = { if (item.route != currentRoute) onItemClick(item.route) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.text,
                            modifier = Modifier.size(24.dp).scale(scale),
                            tint = if (isSelected) Color.White else Color.Gray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.text,
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isSelected) Color.White else Color.Gray
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.DarkGray // Example of a purple indicator

                ),
            )
        }
    }
}
