package com.iagoaf.movieexplorer.src.features.favorites.presentation.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iagoaf.movieexplorer.R
import com.iagoaf.movieexplorer.core.ui.theme.Gray100
import com.iagoaf.movieexplorer.core.ui.theme.Gray700
import com.iagoaf.movieexplorer.core.ui.theme.PurpleLight
import com.iagoaf.movieexplorer.core.ui.theme.White
import com.iagoaf.movieexplorer.core.ui.theme.appTypography
import com.iagoaf.movieexplorer.src.features.favorites.presentation.state.FavoritesState
import com.iagoaf.movieexplorer.src.shared.components.ListMoviesComp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavoritesScreen(navController: NavController, state: FavoritesState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
            .padding(start = 20.dp, end = 20.dp, top = 26.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_favorites),
            modifier = Modifier.size(40.dp),
            contentDescription = "Popular Icon",
            colorFilter = ColorFilter.tint(PurpleLight)
        )

        Text(
            "Favoritos",
            style = appTypography.displayLarge.copy(
                color = White
            ),
            modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
        )
        Text(
            "Sua lista de filmes salvos",
            style = appTypography.labelMedium.copy(
                color = Gray700
            ),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        when (state) {
            is FavoritesState.Error -> {}
            is FavoritesState.Idle -> {}
            is FavoritesState.Loading -> {}
            is FavoritesState.Success -> {
                ListMoviesComp(
                    movies = state.movies,
                    onClickMovie = {},
                    onLoadMore = {},
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun FavoritesScreenPreview() {
    FavoritesScreen(navController = rememberNavController(), state = FavoritesState.Idle)
}