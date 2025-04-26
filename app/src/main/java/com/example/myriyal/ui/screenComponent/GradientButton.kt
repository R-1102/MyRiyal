package com.example.myriyal.ui.screenComponent

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

@Composable
fun GradientButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    gradient: Brush = Brush.linearGradient(
        colorStops = arrayOf(
            0.2f to MaterialTheme.colorScheme.inversePrimary,
            0.9f to MaterialTheme.colorScheme.inverseSurface,
            1.0f to MaterialTheme.colorScheme.inverseOnSurface
        ),
        start = Offset(0f, 0f),
        end = Offset(0f, 130f)
    ),
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(integerResource(id = R.integer.buttonHeight).dp)
            .clip(RoundedCornerShape(integerResource(id = R.integer.buttonRoundCornerShape).dp))
            .background(gradient)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Color.White,
                fontSize = integerResource(id = R.integer.smallText).sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}
