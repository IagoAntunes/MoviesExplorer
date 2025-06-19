package com.iagoaf.movieexplorer.src.shared.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iagoaf.movieexplorer.src.shared.MovieModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListMoviesComp(
    movies: List<MovieModel>,
    onLoadMore: () -> Unit,
    onClickMovie: (MovieModel) -> Unit,
) {
    val listState = rememberLazyGridState()

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            val totalItems = listState.layoutInfo.totalItemsCount
            lastVisible != null && lastVisible.index >= totalItems - 4
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            onLoadMore()
        }
    }

    LazyVerticalGrid(
        state = listState,
        columns = Fixed(2),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) { movie ->
            CardMovieComp(
                movie = movie,
                onClick = { onClickMovie(movie) },
            )
        }
    }
}