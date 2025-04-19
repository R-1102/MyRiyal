package com.example.myriyal.screenComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.example.myriyal.ui.theme.White

@Composable
fun CustomFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    gradient: Brush = Brush.linearGradient(
        colorStops = arrayOf(
            0.2f to MaterialTheme.colorScheme.onSurfaceVariant,
            0.9f to MaterialTheme.colorScheme.surfaceVariant,
        ),
        start = Offset(0f, 0f),
        end = Offset(0f, 130f)
    ),
) {

    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(RoundedCornerShape(integerResource(id = R.integer.floatingButton).dp))
            .background(gradient)
    ) {
        Icon(
            Icons.Filled.Add,
            "Add floating action button.",
            tint = White,
            modifier = Modifier
        )
    }
}