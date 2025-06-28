package com.iagoaf.movieexplorer

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.iagoaf.movieexplorer.core.routes.AppRoutes
import com.iagoaf.movieexplorer.core.routes.ScreenNavItem
import com.iagoaf.movieexplorer.core.ui.theme.Gray100
import com.iagoaf.movieexplorer.core.ui.theme.Gray200
import com.iagoaf.movieexplorer.core.ui.theme.Gray500
import com.iagoaf.movieexplorer.core.ui.theme.MovieExplorerTheme
import com.iagoaf.movieexplorer.core.ui.theme.PurpleLight
import com.iagoaf.movieexplorer.core.ui.theme.appTypography
import com.iagoaf.movieexplorer.src.features.favorites.presentation.screen.FavoritesScreen
import com.iagoaf.movieexplorer.src.features.favorites.presentation.viewmodel.FavoritesViewModel
import com.iagoaf.movieexplorer.src.features.movieDetail.presentation.screen.MovieDetailsScreen
import com.iagoaf.movieexplorer.src.features.movieDetail.presentation.viewmodel.MovieDetailViewModel
import com.iagoaf.movieexplorer.src.features.popular.presentation.screen.PopularScreen
import com.iagoaf.movieexplorer.src.features.popular.presentation.viewmodel.PopularViewModel
import com.iagoaf.movieexplorer.src.features.search.presentation.screen.SearchScreen
import com.iagoaf.movieexplorer.src.features.search.presentation.viewmodel.SearchViewModel
import com.iagoaf.movieexplorer.src.shared.movie.domain.model.MovieModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MovieExplorerTheme {
                NavGraphHost(navController = navController)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraphHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.POPULAR,
    ) {
        composable(AppRoutes.POPULAR) {
            val vm: PopularViewModel = hiltViewModel()
            val state = vm.state.collectAsState().value
            ScreenWithBottomBar(navController) {
                PopularScreen(navController = navController, state = state)
            }
        }
        composable(AppRoutes.SEARCH) {
            val vm: SearchViewModel = hiltViewModel()
            val state = vm.state.collectAsState().value
            ScreenWithBottomBar(navController) {
                SearchScreen(
                    navController = navController,
                    searchState = state,
                    onSearchMovie = { query, reset ->
                        vm.searchMovie(query, reset)
                    }
                )
            }
        }
        composable(AppRoutes.FAVORITES) {
            val vm: FavoritesViewModel = hiltViewModel()
            val state = vm.state.collectAsState().value
            LaunchedEffect(Unit) {
                vm.getFavorites()
            }
            ScreenWithBottomBar(navController) {
                FavoritesScreen(navController = navController, state = state)
            }
        }
        composable(
            route = "${AppRoutes.MOVIE_DETAIL}/{movie}",
            arguments = listOf(navArgument("movie") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val movieArg = backStackEntry.arguments?.getString("movie")
            val decodedMovie = movieArg?.let {
                val json = URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
                Json.decodeFromString<MovieModel>(json)
            }
            val vm: MovieDetailViewModel = hiltViewModel()
            val state = vm.state.collectAsState().value
            val isFavorite by vm.isFavorite.collectAsState()

            MovieDetailsScreen(
                movie = decodedMovie!!,
                navController = navController,
                movieDetailState = state,
                onFavoriteMovie = { vm.toggleFavorite() },
                isFavorite = isFavorite
            )
        }
    }
}

@Composable
fun ScreenWithBottomBar(
    navController: NavHostController,
    content: @Composable (Modifier) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavItems = listOf(
        ScreenNavItem.Popular,
        ScreenNavItem.Search,
        ScreenNavItem.Favorites
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Gray100,
        bottomBar = {
            BottomAppBar(containerColor = Gray200) {
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
                                modifier = Modifier.size(24.dp)
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
        }
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ContentAppPreview() {
    MovieExplorerTheme {

    }
}