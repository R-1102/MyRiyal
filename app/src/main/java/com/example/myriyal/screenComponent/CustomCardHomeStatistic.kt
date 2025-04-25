package com.example.myriyal.screenComponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import com.example.myriyal.R

@Composable
fun CustomCardHomeStatistic(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = integerResource(id = R.integer.smallerSpace).dp),
        shape = RoundedCornerShape(integerResource(id = R.integer.roundCardCornerShape).dp),
        elevation = CardDefaults.cardElevation(integerResource(id = R.integer.homeCardElevation).dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
    ) {
        content()
    }
}