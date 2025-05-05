package org.sopt.at.screen.ui.theme

import android.app.Activity
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
    primary = TvingRed,
    background = TvingBlack,
    surface = TvingBlack,
    onPrimary = TvingWhite,
    onBackground = TvingWhite,
    onSurface = TvingWhite
)

private val LightColorScheme = lightColorScheme(
    primary = TvingRed,
    background = TvingBlack,
    surface = TvingBlack,
    onPrimary = TvingWhite,
    onBackground = TvingWhite,
    onSurface = TvingWhite
)

@Composable
fun ATSOPTANDROIDTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


