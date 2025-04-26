package com.example.myriyal.ui.screenComponent

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
import androidx.compose.ui.res.integerResource
import com.example.myriyal.R

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .padding(integerResource(R.integer.cardPadding).dp)
            .shadow(integerResource(R.integer.customCardShadow).dp)
            .clip(RoundedCornerShape(integerResource(R.integer.customCardRound).dp)),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {
        content()
    }
}

