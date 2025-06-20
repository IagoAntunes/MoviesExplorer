package com.iagoaf.movieexplorer.src.features.movieDetail.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import com.iagoaf.movieexplorer.R
import com.iagoaf.movieexplorer.core.ui.theme.Gray100
import com.iagoaf.movieexplorer.core.ui.theme.Gray300
import com.iagoaf.movieexplorer.core.ui.theme.Gray600
import com.iagoaf.movieexplorer.core.ui.theme.Gray700
import com.iagoaf.movieexplorer.core.ui.theme.PurpleLight
import com.iagoaf.movieexplorer.core.ui.theme.White
import com.iagoaf.movieexplorer.core.ui.theme.appTypography
import com.iagoaf.movieexplorer.src.features.movieDetail.presentation.MovieDetailState
import com.iagoaf.movieexplorer.src.shared.MovieUtils
import com.iagoaf.movieexplorer.src.shared.movie.domain.model.MovieModel

@Composable
fun MovieDetailsScreen(
    movie: MovieModel,
    navController: NavController,
    movieDetailState: MovieDetailState,
    onFavoriteMovie: (MovieModel) -> Unit = { },
    isFavorite: Boolean,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onFavoriteMovie(movie)
                },
                containerColor = if (isFavorite) PurpleLight else Gray300,
                shape = RoundedCornerShape(8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_favorites),
                    contentDescription = "Favorite",
                    colorFilter = ColorFilter.tint(
                        if (isFavorite) White else
                            PurpleLight
                    )
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray100)
                .padding(innerPadding)
                .padding(start = 20.dp, end = 20.dp, top = 26.dp, bottom = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                if (!movie.backdropPath.isNullOrEmpty()) {
                    AsyncImage(
                        model = Builder(LocalContext.current)
                            .data(MovieUtils.imgBackdropBaseUrl + movie.backdropPath)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Gray)
                            .clickable {
                            }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_film),
                            contentDescription = "No Image",
                            modifier = Modifier
                                .size(64.dp)
                                .align(Alignment.Center),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }

                TextButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, top = 12.dp)
                        .align(Alignment.TopStart)
                        .background(
                            color = Gray300,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_left),
                            contentDescription = "Botão",
                            tint = Gray600,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Voltar",
                            style = appTypography.bodyMedium.copy(
                                color = Gray600
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                movie.title,
                style = appTypography.titleLarge.copy(
                    color = Gray700
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Gray600,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            ) {
                                append("Lançamento: ")
                            }
                            withStyle(style = SpanStyle(color = Gray600)) {
                                append(movie.releaseDate)
                            }
                        },
                        style = appTypography.bodySmall
                    )

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Gray600,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            ) {
                                append("Língua: ")
                            }
                            withStyle(style = SpanStyle(color = Gray600)) {
                                append(movie.originalLanguage.uppercase())
                            }
                        },
                        style = appTypography.bodySmall
                    )
                }

                Row {
                    Image(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(PurpleLight),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = String.format("%.1f/10", movie.voteAverage),
                        style = appTypography.titleMedium.copy(
                            color = Gray600
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = movie.overview,
                style = appTypography.bodyMedium.copy(
                    color = Gray600,
                )
            )
        }
    }
}


@Preview
@Composable
private fun MovieDetailsPreview() {
    MovieDetailsScreen(
        navController = rememberNavController(),
        movie = MovieModel(
            id = 1,
            title = "Movie",
            originalTitle = "Original Movie Title",
            overview = "This is a brief overview of the movie. It provides a summary of the plot and main themes.",
            releaseDate = "2023-10-01",
            backdropPath = "/path/to/backdrop.jpg",
            posterPath = "/path/to/poster.jpg",
            genreIds = listOf(1, 2, 3),
            originalLanguage = "en",
            adult = false,
            popularity = 123.45,
            video = false,
            voteAverage = 8.0,
            voteCount = 1000
        ),
        movieDetailState = MovieDetailState.Idle,
        isFavorite = false,
    )
}