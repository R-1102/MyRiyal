package com.example.myriyal.screens.Profile.presentattion.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myriyal.R

import com.example.myriyal.screenComponent.CustomTextField

@Composable
fun EditName(
    currentName: String,
    onNameEntered: (String) -> Unit, // Callback when user confirms the new name
    onDismiss: () -> Unit,           // Callback when user cancels the edit
) {
    // Local state to hold the current input value for the name
    var userName by remember { mutableStateOf(currentName) }

    Column {
        // Text field to allow editing the name
        CustomTextField(
            value = userName,                          // Current value shown in the text field
            onValueChange = { userName = it },         // Updates state as user types
            label = stringResource(R.string.editYourName), // Label for the field
            readOnly = false,                          // Field is editable
            modifier = Modifier.fillMaxWidth(),        // Takes full width
            trailingIcon = {}                          // No trailing icon
        )

        // Spacer to add vertical space between the text field and buttons
        Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

        // Row to hold the "OK" and "Cancel" buttons
        Row(modifier = Modifier.fillMaxWidth()) {
            // OK button: confirms the name and sends it to the parent
            TextButton(
                onClick = { onNameEntered(userName) }
            ) {
                Text(
                    text = stringResource(R.string.ok),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.weight(1f)) // Pushes Cancel button to the right end

            // Cancel button: closes the dialog without saving
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
