package com.iagoaf.movieexplorer.src.shared.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import com.iagoaf.movieexplorer.R
import com.iagoaf.movieexplorer.core.ui.theme.White
import com.iagoaf.movieexplorer.core.ui.theme.appTypography
import com.iagoaf.movieexplorer.src.shared.MovieModel
import com.iagoaf.movieexplorer.src.shared.MovieUtils

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardMovieComp(
    movie: MovieModel,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f / 3f)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(
            16.dp
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (!movie.posterPath.isNullOrBlank()) {
                AsyncImage(
                    model = Builder(LocalContext.current)
                        .data(MovieUtils.imgPosterBaseUrl + movie.posterPath)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Poster do Filme",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
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
                        if (movie.releaseDate.isEmpty()) "" else movie.formatReleaseDate().year.toString(),
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun CardMoviePrev() {
    CardMovieComp(
        movie = MovieModel(
            adult = false,
            backdropPath = "",
            genreIds = emptyList(),
            id = 0,
            originalLanguage = "",
            originalTitle = "Movie Title",
            overview = "This is a brief overview of the movie. It gives a short description of the plot and main characters.",
            popularity = 0.0,
            posterPath = null,
            releaseDate = "2023-10-01",
            title = "Movie Title",
            video = false,
            voteAverage = 8.5,
            voteCount = 1000
        )
    )
}