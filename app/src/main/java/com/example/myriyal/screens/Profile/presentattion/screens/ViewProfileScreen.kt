package com.example.myriyal.screens.Profile.presentattion.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myriyal.R
import com.example.myriyal.screenComponent.CustomCard
import com.example.myriyal.screenComponent.CustomDialog
import com.example.myriyal.screenComponent.CustomTextField
import com.example.myriyal.screens.Profile.presentattion.vmModels.ProfileViewModel

@Composable
fun ViewProfile() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.height(integerResource(R.integer.profileTopperSpace).dp))

        // a static profile icon
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(integerResource(R.integer.profileIconSize).dp),
            tint = MaterialTheme.colorScheme.secondary,
        )
        Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))
        CustomCard(
            modifier = Modifier.padding(integerResource(R.integer.padding).dp),
        ) {

            val profileViewModel: ProfileViewModel = hiltViewModel()


            val showNameEditor = remember { mutableStateOf(false) }
            val showBalanceEditor = remember { mutableStateOf(false) }

            //We need to use VM after implementing it instead of remember
            // the default name should be the user name from the DB
            var userName by profileViewModel.userName
            // the default balance should be the user balance from the DB
            var balance by profileViewModel.balance

            //Row for the user name
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(integerResource(R.integer.padding).dp)

            ) {
                Text(
                    text = stringResource(R.string.userName),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Spacer(Modifier.weight(1f))
                CustomTextField(
                    value = userName,
                    onValueChange = {},
                    label = "",
                    readOnly = true,
                    modifier = Modifier.width(integerResource(R.integer.fieldWidth).dp),
                    //icon to edit the name
                    trailingIcon = {
                        IconButton(
                            onClick = { showNameEditor.value = true }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.BorderColor,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                            )
                        }
                    }
                )
                if (showNameEditor.value) {
                    CustomDialog(
                        shouldShowDialog = showNameEditor,
                        content = {
                            EditName(
                                currentName = userName,
                                onNameEntered = { enteredName: String ->
                                    profileViewModel.updateUserName(enteredName)
                                    showNameEditor.value = false
                                },
                                onDismiss = { showNameEditor.value = false }
                            )
                        }
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(integerResource(R.integer.padding).dp),
            ) {
                Text(
                    stringResource((R.string.balance)),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Spacer(Modifier.weight(1f))
                CustomTextField(
                    value = balance.toString(),
                    onValueChange = {},
                    label = "",
                    readOnly = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.width(integerResource(R.integer.fieldWidth).dp),
                    trailingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            //Riyal icon
                            Icon(
                                painter = painterResource(R.drawable.income),
                                contentDescription = "Saudi Riyal symbol",
                                modifier = Modifier.size(20.dp)
                            )
                            //icon to edit the balance
                            IconButton(
                                onClick = { showBalanceEditor.value = true }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.BorderColor,
                                    contentDescription = "Editing icon",
                                    tint = MaterialTheme.colorScheme.secondary,
                                )
                            }
                        }
                    }
                )
                if (showBalanceEditor.value) {
                    CustomDialog(
                        shouldShowDialog = showBalanceEditor,
                        content = {
                            EditBalance(
                                currentBalance = balance,
                                onBalanceEntered = { enteredBalance ->
                                    profileViewModel.updateUserBalance(enteredBalance)
                                    showBalanceEditor.value = false
                                },
                                onDismiss = { showBalanceEditor.value = false }
                            )
                        }
                    )
                }
            }
        }
    }
}