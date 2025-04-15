package com.example.myriyal.screenComponent

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
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myriyal.R
import com.example.myriyal.ui.theme.firstDarkGreen
import com.example.myriyal.ui.theme.firstGreen
import com.example.myriyal.ui.theme.secondDarkGreen
import com.example.myriyal.ui.theme.secondGreen
import com.example.myriyal.ui.theme.thirdDarkGreen
import com.example.myriyal.ui.theme.thirdGreen

@Composable
fun GradientButton(
    onClick: () -> Unit,
    text: String,
    lightGradient: Brush = Brush.linearGradient(
        colorStops = arrayOf(
            0.2f to firstGreen,
            0.9f to secondGreen,
            1.0f to thirdGreen
        ),
        start = Offset(0f, 0f),
        end = Offset(0f, 130f)
    ),
    darkGradient: Brush = Brush.linearGradient(
        colorStops = arrayOf(
            0.2f to firstDarkGreen,
            0.9f to secondDarkGreen,
            1.0f to thirdDarkGreen
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
