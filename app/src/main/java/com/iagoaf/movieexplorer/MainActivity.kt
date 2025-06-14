package com.iagoaf.movieexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iagoaf.movieexplorer.core.routes.AppRoutes
import com.iagoaf.movieexplorer.core.routes.ScreenNavItem
import com.iagoaf.movieexplorer.src.features.favorites.presentation.screen.FavoritesScreen
import com.iagoaf.movieexplorer.src.features.popular.presentation.screen.PopularScreen
import com.iagoaf.movieexplorer.src.features.search.presentation.screen.SearchScreen
import com.iagoaf.movieexplorer.ui.theme.Gray100
import com.iagoaf.movieexplorer.ui.theme.Gray200
import com.iagoaf.movieexplorer.ui.theme.Gray500
import com.iagoaf.movieexplorer.ui.theme.MovieExplorerTheme
import com.iagoaf.movieexplorer.ui.theme.PurpleLight
import com.iagoaf.movieexplorer.ui.theme.appTypography

// Se você estiver usando a Abordagem 2 para tipografia personalizada:
// import com.iagoaf.movieexplorer.ui.theme.LocalAppCustomTypography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MovieExplorerTheme {
                ContentApp(navController = navController)
            }
        }
    }
}

@Composable
fun ContentApp(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Gray100,
        bottomBar = {
            BottomAppBar(
                containerColor = Gray200
            ) {
                val bottomNavItems = listOf(
                    ScreenNavItem.Popular,
                    ScreenNavItem.Search,
                    ScreenNavItem.Favorites
                )

                bottomNavItems.forEach { screen ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PurpleLight,
                            unselectedIconColor = Gray500,
                            selectedTextColor = PurpleLight,
                            unselectedTextColor = Gray500,
                            indicatorColor = Color.Transparent
                        ),
                        selected = isSelected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.iconResId),
                                contentDescription = screen.label,
                                tint = if (isSelected) PurpleLight else Gray500,
                                modifier = Modifier.size(24.dp),
                            )
                        },
                        label = {
                            Text(
                                screen.label,
                                style = appTypography.labelSmall.copy(
                                    fontSize = 12.sp,
                                    color = if (isSelected) PurpleLight else Gray500
                                )
                            )
                        }
                    )
                }
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoutes.POPULAR,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = AppRoutes.POPULAR) {
                PopularScreen(navController = navController)
            }
            composable(route = AppRoutes.SEARCH) {
                SearchScreen(navController = navController)
            }
            composable(route = AppRoutes.FAVORITES) {
                FavoritesScreen(navController = navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ContentAppPreview() {
    MovieExplorerTheme {
        ContentApp(navController = rememberNavController())
    }
}