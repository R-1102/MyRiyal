package com.example.myriyal.screenComponent

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.myriyal.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DatePickerModal(
    onDateSelected: (Long?) -> Unit, onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    val context = LocalContext.current

    DatePickerDialog(onDismissRequest = onDismiss, confirmButton = {
        TextButton(onClick = {
            onDateSelected(datePickerState.selectedDateMillis)
            onDismiss()
        }) {
            Text(context.getString(R.string.ok))
        }
    }, dismissButton = {
        TextButton(onClick = onDismiss) {
            Text(context.getString(R.string.cancel))
        }
    }) {
        DatePicker(state = datePickerState)
    }
}