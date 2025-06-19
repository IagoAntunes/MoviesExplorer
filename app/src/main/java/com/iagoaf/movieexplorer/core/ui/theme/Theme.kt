package com.iagoaf.movieexplorer.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PurpleBase,
    secondary = PurpleLight,
    tertiary = Gray500,
    background = Gray100,
    surface = Gray200,
    onPrimary = White,
    onSecondary = White,
    onTertiary = Gray700,
    onBackground = Gray700,
    onSurface = Gray600
)

private val LightColorScheme = lightColorScheme(
    primary = PurpleBase,
    secondary = PurpleLight,
    tertiary = Gray500,
    background = Gray700,
    surface = Gray600,
    onPrimary = Gray100,
    onSecondary = Gray100,
    onTertiary = Gray200,
    onBackground = Gray100,
    onSurface = Gray300
)

@Composable
fun MovieExplorerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = appTypography,
        content = content
    )
}