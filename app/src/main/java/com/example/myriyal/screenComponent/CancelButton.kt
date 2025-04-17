package com.example.myriyal.screenComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myriyal.R

@Composable
fun CancelButton(
    onClick: () -> Unit,
    text: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(integerResource(id = R.integer.buttonHeight).dp)
            .clip(RoundedCornerShape(integerResource(id = R.integer.roundCornerShape).dp))
            .background(Transparent)
            .clickable(onClick = onClick)
            .border(3.dp, MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
                color = MaterialTheme.colorScheme.primary,
                fontSize = integerResource(id= R.integer.smallText).sp,
                fontWeight = FontWeight.Bold
            )
    }
}