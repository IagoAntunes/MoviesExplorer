package com.iagoaf.movieexplorer.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.iagoaf.movieexplorer.R


val rammettoOne = FontFamily(
    Font(R.font.rammetto_one_regular, FontWeight.Normal)
)

val rajdhani = FontFamily(
    Font(R.font.rajdhani_bold, FontWeight.Bold),
)
val nunitoSans = FontFamily(
    Font(R.font.nunito_sans_variable, FontWeight.Bold),
    Font(R.font.nunito_sans_variable, FontWeight.Normal),
)


val appTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = rammettoOne,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = rajdhani,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = rajdhani,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = nunitoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = nunitoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
)