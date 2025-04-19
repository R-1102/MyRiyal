package com.example.myriyal.screens.Profile.presentattion.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.example.myriyal.screenComponent.CustomTextField

@Composable
fun EditBalance(currentBalance: Double, onBalanceEntered: (Double) -> Unit, onDismiss: () -> Unit,) {

    // Holds the local state for the editable balance value
    val userBalance = remember{ mutableDoubleStateOf(currentBalance) }

    Column {
        // Input field for editing the balance
        CustomTextField(
            value = userBalance.doubleValue.toString(), // Shows the current balance as text
            onValueChange = {
                // Updates the balance state, handles invalid inputs gracefully
                userBalance.doubleValue= it.toDoubleOrNull() ?: currentBalance
            },
            label = stringResource(R.string.editYourName), // Label shown inside the text field (Note: may be incorrect label for balance)
            readOnly = false, // Allows editing
            modifier = Modifier.fillMaxWidth(), // Text field takes full width
            trailingIcon = {}, // No trailing icon
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal) // Brings up decimal keyboard
        )

        Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp)) // Adds vertical spacing

        // Row that holds the action buttons
        Row (modifier = Modifier.fillMaxWidth()) {
            // "OK" button to confirm and submit the new balance
            TextButton(
                onClick = { onBalanceEntered(userBalance.doubleValue) }, // Passes the entered balance to the parent
                content = {
                    Text(
                        stringResource(R.string.ok),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                }
            )

            Spacer(Modifier.weight(1f)) // Pushes the Cancel button to the end

            // "Cancel" button to dismiss the dialog without saving
            TextButton(
                onClick = { onDismiss() }, // Dismiss callback
                content = {
                    Text(
                        stringResource(R.string.cancel),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    }
}