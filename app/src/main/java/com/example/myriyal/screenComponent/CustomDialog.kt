package com.example.myriyal.screenComponent

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun CustomDialog(
    shouldShowDialog: MutableState<Boolean>,
    content: @Composable () -> Unit
) {
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = { shouldShowDialog.value = false },
            text = { content() },
            confirmButton = {},
            containerColor =  MaterialTheme.colorScheme.surface,
        )
    }
}
