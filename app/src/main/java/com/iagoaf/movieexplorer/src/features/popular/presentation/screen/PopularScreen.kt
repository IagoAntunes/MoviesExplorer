package com.iagoaf.movieexplorer.src.features.popular.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import coil.request.ImageRequest
import com.iagoaf.movieexplorer.R
import com.iagoaf.movieexplorer.ui.theme.Gray100
import com.iagoaf.movieexplorer.ui.theme.Gray600
import com.iagoaf.movieexplorer.ui.theme.Gray700
import com.iagoaf.movieexplorer.ui.theme.PurpleLight
import com.iagoaf.movieexplorer.ui.theme.White
import com.iagoaf.movieexplorer.ui.theme.appTypography

@Composable
fun PopularScreen(navController: NavController) {
    // Exemplo de URL de imagem (substitua pela sua URL real)
    val exampleImageUrl =
        "https://br.web.img2.acsta.net/pictures/21/08/16/10/00/0453990.jpg" // Exemplo

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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(6) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(exampleImageUrl) // Use sua URL dinâmica aqui
                                .crossfade(true)
                                .placeholder(R.drawable.image)
                                .build(),
                            contentDescription = "Poster do Filme ${index + 1}",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )


                        // Sombra no fundo para deixar o texto legível
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .align(Alignment.BottomCenter)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.6f),
                                            Color.Black.copy(alpha = 0.9f)
                                        ),
                                        startY = 300f // Pode ajustar conforme a altura do seu card
                                    )
                                )
                        )

                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "Missão: Impossível - O Acerto Final",
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
                                    "7,5",
                                    style = appTypography.labelSmall.copy(
                                        color = Gray600,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.W600
                                    )
                                )
                                Text(
                                    " - ",
                                    style = appTypography.labelSmall.copy(
                                        color = Gray600,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.W600
                                    )
                                )
                                Text(
                                    "2025",
                                    style = appTypography.labelSmall.copy(
                                        color = Gray600,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.W600
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PopularScreenPreview() {
    PopularScreen(navController = rememberNavController())
}