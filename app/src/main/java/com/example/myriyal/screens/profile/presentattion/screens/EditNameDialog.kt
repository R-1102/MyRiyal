package com.example.myriyal.screens.profile.presentattion.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.example.myriyal.ui.screenComponent.CancelButton

import com.example.myriyal.ui.screenComponent.CustomTextField
import com.example.myriyal.ui.screenComponent.GradientButton

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
        // Row that holds the action buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = integerResource(R.integer.smallSpace).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // "OK" button to confirm and submit the new balance
            GradientButton(
                onClick = { onNameEntered(userName) }, // Passes the entered balance to the parent
                text = stringResource(R.string.ok),
                modifier = Modifier
                    .size(80.dp, 40.dp)
            )
            // "Cancel" button to dismiss the dialog without saving
            CancelButton(
                onClick = { onDismiss() },
                text = stringResource(R.string.cancel),
                modifier = Modifier
                    .size(80.dp, 40.dp)
            )
        }
    }
}
