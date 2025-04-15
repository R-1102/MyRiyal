package com.example.myriyal.screenComponent

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    colors: CardColors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .shadow(30.dp)
            .clip(RoundedCornerShape(8.dp)),
        colors = colors
    ) {
        content()
    }
}

