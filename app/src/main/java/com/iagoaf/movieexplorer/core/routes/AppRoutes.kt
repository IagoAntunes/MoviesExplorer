package com.iagoaf.movieexplorer.core.routes

import com.iagoaf.movieexplorer.R

// AppRoutes.kt
object AppRoutes {
    const val POPULAR = "popular"
    const val SEARCH = "search"
    const val FAVORITES = "favorites"
    const val MOVIE_DETAIL = "movieDetail"

}

sealed class ScreenNavItem(val route: String, val label: String, val iconResId: Int) {
    object Popular : ScreenNavItem(AppRoutes.POPULAR, "Popular", R.drawable.ic_film)
    object Search : ScreenNavItem(AppRoutes.SEARCH, "Search", R.drawable.ic_search)
    object Favorites : ScreenNavItem(AppRoutes.FAVORITES, "Favorites", R.drawable.ic_favorites)
}