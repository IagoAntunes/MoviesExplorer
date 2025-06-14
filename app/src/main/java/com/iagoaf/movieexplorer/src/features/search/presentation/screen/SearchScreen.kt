package com.iagoaf.movieexplorer.src.features.search.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iagoaf.movieexplorer.R
import com.iagoaf.movieexplorer.ui.theme.Gray100
import com.iagoaf.movieexplorer.ui.theme.Gray500
import com.iagoaf.movieexplorer.ui.theme.Gray700
import com.iagoaf.movieexplorer.ui.theme.PurpleLight
import com.iagoaf.movieexplorer.ui.theme.White
import com.iagoaf.movieexplorer.ui.theme.appTypography

@Composable
fun SearchScreen(navController: NavController) {
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
        TextField(
            modifier = Modifier
                .fillMaxWidth()
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
            shape = RoundedCornerShape(16.dp),
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    "Pesquisar filme",
                    style = appTypography.labelMedium.copy(
                        color = Gray500
                    )
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
        )
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(navController = rememberNavController())
}