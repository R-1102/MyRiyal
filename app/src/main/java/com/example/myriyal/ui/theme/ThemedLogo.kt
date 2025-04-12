package com.example.myriyal.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.myriyal.R

@Composable
fun ThemedLogo(
    modifier: Modifier = Modifier,
    lightLogoRes: Int = R.drawable.light_logo_png,
    darkLogoRes: Int = R.drawable.dark_logo_png,
    contentDescription: String = "App Logo"
) {
    val isDark = isSystemInDarkTheme()
    val logoPainter = painterResource(id = if (isDark) darkLogoRes else lightLogoRes)

    Image(
        painter = logoPainter,
        contentDescription = contentDescription,
        modifier = modifier
    )
}