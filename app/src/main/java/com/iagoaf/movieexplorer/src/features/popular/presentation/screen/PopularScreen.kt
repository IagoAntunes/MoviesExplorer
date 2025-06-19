package com.iagoaf.movieexplorer.src.features.popular.presentation.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import com.iagoaf.movieexplorer.R
import com.iagoaf.movieexplorer.core.ui.theme.Gray100
import com.iagoaf.movieexplorer.core.ui.theme.Gray700
import com.iagoaf.movieexplorer.core.ui.theme.PurpleLight
import com.iagoaf.movieexplorer.core.ui.theme.White
import com.iagoaf.movieexplorer.core.ui.theme.appTypography
import com.iagoaf.movieexplorer.src.features.popular.presentation.state.PopularViewModelState
import com.iagoaf.movieexplorer.src.shared.MovieModel
import com.iagoaf.movieexplorer.src.shared.MovieUtils

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
                LazyVerticalGrid(
                    columns = Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.movies) { movie ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(2f / 3f),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                AsyncImage(
                                    model = Builder(LocalContext.current)
                                        .data(MovieUtils.imgBaseUrl + movie.posterPath)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = "Poster do Filme",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )

                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .fillMaxWidth()
                                        .background(
                                            Brush.verticalGradient(
                                                tileMode = TileMode.Mirror,
                                                colors = listOf(
                                                    Color.Black.copy(alpha = 0.3f),
                                                    Color.Black.copy(alpha = 0.8f),
                                                ),
                                                startY = 0f,
                                                endY = Float.POSITIVE_INFINITY
                                            )
                                        )
                                        .clip(
                                            shape = RoundedCornerShape(16.dp)
                                        )

                                        .padding(12.dp)
                                ) {
                                    Text(
                                        text = movie.title,
                                        style = appTypography.titleMedium.copy(
                                            color = White,
                                            fontSize = 16.sp
                                        ),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Row {
                                        Image(
                                            painter = painterResource(R.drawable.ic_star),
                                            contentDescription = "Rating Star",
                                            modifier = Modifier
                                                .size(12.dp)
                                                .align(Alignment.CenterVertically),
                                            colorFilter = ColorFilter.tint(White)
                                        )
                                        Text(
                                            movie.voteAverage.toString(),
                                            style = appTypography.labelSmall.copy(
                                                color = White,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.W800
                                            )
                                        )
                                        Text(
                                            " - ",
                                            style = appTypography.labelSmall.copy(
                                                color = White,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.W800
                                            )
                                        )
                                        Text(
                                            movie.formatReleaseDate().year.toString(),
                                            style = appTypography.labelSmall.copy(
                                                color = White,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.W800
                                            )
                                        )
                                    }
                                }
                            }
                        }

                    }
                }
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