package com.iagoaf.movieexplorer.src.features.popular.presentation.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iagoaf.movieexplorer.R
import com.iagoaf.movieexplorer.core.routes.AppRoutes
import com.iagoaf.movieexplorer.core.ui.theme.Gray100
import com.iagoaf.movieexplorer.core.ui.theme.Gray700
import com.iagoaf.movieexplorer.core.ui.theme.PurpleLight
import com.iagoaf.movieexplorer.core.ui.theme.White
import com.iagoaf.movieexplorer.core.ui.theme.appTypography
import com.iagoaf.movieexplorer.src.features.popular.presentation.state.PopularViewModelState
import com.iagoaf.movieexplorer.src.shared.movie.domain.model.MovieModel
import com.iagoaf.movieexplorer.src.shared.components.ListMoviesComp
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PopularScreen(
    navController: NavController,
    state: PopularViewModelState,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
            .padding(start = 20.dp, end = 20.dp, top = 26.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_film),
            modifier = Modifier.size(40.dp),
            contentDescription = "Popular Icon",
            colorFilter = ColorFilter.tint(PurpleLight)
        )

        Text(
            "Populares",
            style = appTypography.displayLarge.copy(
                color = White
            ),
            modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
        )
        Text(
            "Explore os filmes populares hoje e encontre coisas novas para assistir!",
            style = appTypography.labelMedium.copy(
                color = Gray700
            ),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        when (state) {
            is PopularViewModelState.Success -> {
                ListMoviesComp(
                    movies = state.movies,
                    onLoadMore = {

                    },
                    onClickMovie = { movie ->
                        val movieJson = URLEncoder.encode(Json.encodeToString(movie), StandardCharsets.UTF_8.toString())
                        navController.navigate("movieDetail/$movieJson")

                    }
                )
            }

            is PopularViewModelState.Error -> Text("ERRO")
            is PopularViewModelState.Loading -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun PopularScreenPreview() {
    PopularScreen(
        navController = rememberNavController(),
        state = PopularViewModelState.Success(
            listOf(
                MovieModel(
                    adult = false,
                    backdropPath = "",
                    genreIds = listOf(),
                    id = 1,
                    originalLanguage = "en",
                    originalTitle = "Example Movie",
                    overview = "This is an example movie description.",
                    popularity = 10.0,
                    posterPath = "example.jpg",
                    releaseDate = "2023-10-01",
                    title = "Example Movie",
                    video = false,
                    voteAverage = 8.5,
                    voteCount = 1000
                )
            )
        )
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun PopularScreenLoadingPreview() {
    PopularScreen(
        navController = rememberNavController(),
        state = PopularViewModelState.Loading
    )
}