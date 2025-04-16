package com.example.myriyal.screenComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.example.myriyal.ui.theme.firstDarkAdd
import com.example.myriyal.ui.theme.firstGreen
import com.example.myriyal.ui.theme.secondDarkAdd
import com.example.myriyal.ui.theme.secondGreen
import com.example.myriyal.ui.theme.thirdGreen

@Composable
fun CustomFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
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
            0.2f to firstDarkAdd,
            0.9f to secondDarkAdd,
        ),
        start = Offset(0f, 0f),
        end = Offset(0f, 130f)
    ),
) {
    val isDarkTheme = isSystemInDarkTheme()
    val gradient = if (isDarkTheme) darkGradient else lightGradient

    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(RoundedCornerShape(integerResource(id= R.integer.floatingButton).dp))
            .background(gradient)
    ) {
        Icon(
            Icons.Filled.Add,
            "Add floating action button.",
            tint = Color.White,
            modifier = Modifier
        )
    }
}

