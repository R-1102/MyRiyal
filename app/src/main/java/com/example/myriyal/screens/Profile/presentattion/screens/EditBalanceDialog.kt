package com.example.myriyal.screens.Profile.presentattion.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
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

    val userBalance = remember{ mutableDoubleStateOf(currentBalance) }

    Column {
        CustomTextField(
            value = userBalance.doubleValue.toString(),
            onValueChange = {userBalance.doubleValue= it.toDoubleOrNull() ?: currentBalance},
            label = stringResource(R.string.editYourName),
            readOnly = false,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

        Row (modifier = Modifier.fillMaxWidth()) {
            TextButton(
                onClick = { onBalanceEntered(userBalance.doubleValue) },
                content = {
                    Text(
                        stringResource(R.string.ok),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
            Spacer(Modifier.weight(1f))
            TextButton(
                onClick = { onDismiss() },
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