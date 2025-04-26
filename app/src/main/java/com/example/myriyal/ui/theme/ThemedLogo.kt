package com.example.myriyal.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myriyal.R

@Composable
fun ThemedLogo(
    modifier: Modifier = Modifier,
    lightLogoRes: Int = R.drawable.light_logo_png,
    darkLogoRes: Int = R.drawable.dark_logo_png,
) {
    val logoPainter = painterResource(id = if (isSystemInDarkTheme()) darkLogoRes else lightLogoRes)

    Image(
        painter = logoPainter,
        contentDescription = "App Logo",
        modifier = modifier.size(
            integerResource(id = R.integer.logoSizeWidth).dp,
            integerResource(id = R.integer.logoSizeHeight).dp
        ),
    )
}