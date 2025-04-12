package com.example.myriyal.screens.authentication.presentation.component

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GradientButton(
    onClick: () -> Unit,
    text: String,

    lightGradient: Brush = Brush.linearGradient(
        colorStops = arrayOf(
            0.2f to Color(0xC9005732),
            0.9f to Color(0xEB004D2C),
            1.0f to Color(0xFFF00532F)
        ),
        start = Offset(0f, 0f),
        end = Offset(0f, 130f)
    ),
    darkGradient: Brush = Brush.linearGradient(
        colorStops = arrayOf(
            0.2f to Color(0xC9082B27),
            0.9f to Color(0xEB082B27),
            1.0f to Color(0xFFF082B27)
        ),
        start = Offset(0f, 0f),
        end = Offset(0f, 130f)
    )
) {
    val isDarkTheme = isSystemInDarkTheme()
    val gradient = if (isDarkTheme) darkGradient else lightGradient

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .height(45.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(gradient)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}
//