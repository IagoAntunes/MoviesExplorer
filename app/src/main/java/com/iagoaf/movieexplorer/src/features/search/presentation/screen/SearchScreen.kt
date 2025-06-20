package com.iagoaf.movieexplorer.src.features.search.presentation.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iagoaf.movieexplorer.R
import com.iagoaf.movieexplorer.core.routes.AppRoutes
import com.iagoaf.movieexplorer.core.ui.theme.Gray100
import com.iagoaf.movieexplorer.core.ui.theme.Gray500
import com.iagoaf.movieexplorer.core.ui.theme.Gray700
import com.iagoaf.movieexplorer.core.ui.theme.PurpleLight
import com.iagoaf.movieexplorer.core.ui.theme.White
import com.iagoaf.movieexplorer.core.ui.theme.appTypography
import com.iagoaf.movieexplorer.src.features.search.presentation.state.SearchState
import com.iagoaf.movieexplorer.src.shared.movie.domain.model.MovieModel
import com.iagoaf.movieexplorer.src.shared.components.ListMoviesComp
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchScreen(
    navController: NavController,
    searchState: SearchState,
    onSearchMovie: (s: String, reset: Boolean) -> Unit = { _, _ -> }
) {
    var searchQuery by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
            .padding(start = 20.dp, end = 20.dp, top = 26.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_search),
            modifier = Modifier.size(40.dp),
            contentDescription = "Popular Icon",
            colorFilter = ColorFilter.tint(PurpleLight)
        )

        Text(
            "Buscar",
            style = appTypography.displayLarge.copy(
                color = White
            ),
            modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
        )
        Text(
            "Encontre filmes buscando pelo título",
            style = appTypography.labelMedium.copy(
                color = Gray700
            ),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Gray100, RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = Gray500,
                        shape = RoundedCornerShape(8.dp)
                    ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Gray100,
                    unfocusedContainerColor = Gray100,
                ),
                textStyle = appTypography.labelMedium.copy(color = White),
                shape = RoundedCornerShape(16.dp),
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                placeholder = {
                    Text(
                        "Pesquisar filme",
                        style = appTypography.labelMedium.copy(color = Gray500)
                    )
                },
                leadingIcon = {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = "Search Icon",
                        colorFilter = ColorFilter.tint(Gray500)
                    )
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    onSearchMovie(searchQuery, true)
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(PurpleLight),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Clear Search",
                    tint = White,
                    modifier = Modifier.size(24.dp)
                )
            }

        }

        Spacer(modifier = Modifier.height(40.dp))

        when (searchState) {
            is SearchState.Error -> Text(
                text = searchState.message,
                modifier = Modifier.fillMaxSize(),
                color = Color.Red
            )

            SearchState.Loading -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }

            is SearchState.Success -> {
                ListMoviesComp(
                    movies = searchState.movies,
                    onLoadMore = {
                        onSearchMovie(searchQuery, false)
                    },
                    onClickMovie = { movie ->
                        val movieJson = URLEncoder.encode(Json.encodeToString(movie), StandardCharsets.UTF_8.toString())
                        navController.navigate("movieDetail/$movieJson")

                    }
                )
            }

            SearchState.Idle -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_list_bullets),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Gray500),
                        modifier = Modifier.size(44.dp)
                    )
                    Text(
                        text = "Nenhuma pesquisa realizada",
                        style = appTypography.titleMedium.copy(
                            color = Gray500,
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
private fun SearchScreenPreview() {
    SearchScreen(
        navController = rememberNavController(),
        searchState = SearchState.Success(emptyList())
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun SearchScreenIdlePreview() {
    SearchScreen(
        navController = rememberNavController(),
        searchState = SearchState.Idle
    )
}