package com.example.myriyal.screens.profile.presentattion.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.example.myriyal.ui.screenComponent.CancelButton
import com.example.myriyal.ui.screenComponent.CustomTextField
import com.example.myriyal.ui.screenComponent.GradientButton

@Composable
fun EditBalance(
    currentBalance: Double,
    onBalanceEntered: (Double) -> Unit,
    onDismiss: () -> Unit,
) {
    // Holds the local state for the editable balance value
    val userBalance = remember { mutableDoubleStateOf(currentBalance) }

    Column {
        CustomTextField(
            value = "${userBalance.doubleValue}",
            onValueChange = {
                // Updates the balance state, handles invalid inputs gracefully
                userBalance.doubleValue = it.toDoubleOrNull() ?: currentBalance
            },
            label = stringResource(R.string.editYourBalance),
            readOnly = false,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {}, // No icon
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal) // Brings up decimal keyboard
        )

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
                onClick = { onBalanceEntered(userBalance.doubleValue) },
                text = stringResource(R.string.ok),
                modifier = Modifier
                    .size(
                        integerResource(id = R.integer.smallButtonHeight).dp,
                        integerResource(id = R.integer.smallButtonWidth).dp
                    )
            )
            // "Cancel" button to dismiss the dialog without saving
            CancelButton(
                onClick = { onDismiss() },
                text = stringResource(R.string.cancel),
                modifier = Modifier
                    .size(
                        integerResource(id = R.integer.smallButtonHeight).dp,
                        integerResource(id = R.integer.smallButtonWidth).dp
                    )
            )
        }
    }
}