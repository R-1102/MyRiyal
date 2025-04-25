package com.example.myriyal.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myriyal.R

@Composable
fun ViewMoreComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val layoutDirection = LocalLayoutDirection.current
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = integerResource(id = R.integer.smallerSpace).dp)
            .clickable { onClick() }) {
        Text(
            text = stringResource(id = R.string.viewMore),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            textDecoration = TextDecoration.Underline,
        )
        Icon(
            imageVector = if (layoutDirection == LayoutDirection.Ltr)
                Icons.Filled.KeyboardDoubleArrowRight
            else
                Icons.Filled.KeyboardDoubleArrowLeft,
            contentDescription = "View More",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}