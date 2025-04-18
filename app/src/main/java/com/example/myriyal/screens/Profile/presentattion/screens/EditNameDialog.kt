package com.example.myriyal.screens.Profile.presentattion.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import com.example.myriyal.screenComponent.CancelButton
import com.example.myriyal.screenComponent.CustomTextField
import com.example.myriyal.screenComponent.GradientButton

@Composable
fun EditName(currentName: String, onNameEntered: (String) -> Unit, onDismiss: () -> Unit,) {

    val userName = remember{ mutableStateOf(currentName) }

    Column {
        CustomTextField(
            value = userName.value,
            onValueChange = {userName.value=it},
            label = stringResource(R.string.editYourName),
            readOnly = false,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {}
        )
        Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

        Row (modifier = Modifier.fillMaxWidth()) {
            TextButton(
                onClick = { onNameEntered(userName.value) },
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