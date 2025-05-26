package org.sopt.at

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = TvingRed,
    background = TvingBlack,
    surface = TvingBlack,
    onPrimary = TvingWhite,
    onBackground = TvingWhite,
    onSurface = TvingWhite,
    onSurfaceVariant = Color.Gray,
    outline = Color(0xFFAAAAAA)
)

private val LightColorScheme = lightColorScheme(
    primary = TvingRed,
    background = TvingBlack,
    surface = TvingBlack,
    onPrimary = TvingWhite,
    onBackground = TvingWhite,
    onSurface = TvingWhite,
    onSurfaceVariant = Color.Gray,
    outline = Color(0xFFAAAAAA)
)

@Composable
fun ATSOPTANDROIDTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


