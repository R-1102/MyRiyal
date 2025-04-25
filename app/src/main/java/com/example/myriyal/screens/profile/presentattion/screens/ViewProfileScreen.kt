package com.example.myriyal.screens.profile.presentattion.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myriyal.R
import com.example.myriyal.screenComponent.CustomCard
import com.example.myriyal.screenComponent.CustomDialog
import com.example.myriyal.screenComponent.CustomTextField
import com.example.myriyal.screens.profile.presentattion.vmModels.ProfileViewModel

@Composable
fun ViewProfile() {
    val profileViewModel: ProfileViewModel = hiltViewModel()

    val showNameEditor = remember { mutableStateOf(false) }
    val showBalanceEditor = remember { mutableStateOf(false) }

    val userName by profileViewModel.userName
    val balance by profileViewModel.balance

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = integerResource(R.integer.extraSmallSpace).dp)
            .clip(RoundedCornerShape(integerResource(id = R.integer.profileRoundedBox).dp))
            .background(MaterialTheme.colorScheme.primary)
            .height(integerResource(id = R.integer.profileBoxHeight).dp)
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = integerResource(id = R.integer.profileTopPadding).dp)
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Account Icon",
            modifier = Modifier
                .size(integerResource(id = R.integer.profileIconSize).dp),
            tint = MaterialTheme.colorScheme.surface,
        )

        Spacer(modifier = Modifier.height(integerResource(id = R.integer.extraLargeSpace).dp))

        CustomCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(integerResource(id = R.integer.profileCustomCardHeight).dp)
                .padding(horizontal = integerResource(R.integer.extraLargeSpace).dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(integerResource(R.integer.mediumSpace).dp)
            ) {
                Spacer(Modifier.height(integerResource(id = R.integer.smallerSpace).dp))
                CustomTextField(
                    value = userName,
                    onValueChange = {},
                    label = stringResource(R.string.userName),
                    readOnly = true,
                    //icon to edit the name
                    trailingIcon = {
                        IconButton(
                            onClick = { showNameEditor.value = true }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.BorderColor,
                                contentDescription = "Edit Icon",
                                tint = MaterialTheme.colorScheme.primary,
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

                Spacer(Modifier.height(integerResource(id = R.integer.extraLargeSpace).dp))

                CustomTextField(
                    value = balance.toString(),
                    onValueChange = {},
                    label = stringResource(R.string.userBalance),
                    readOnly = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    trailingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            //Riyal icon
                            Icon(
                                painter = painterResource(R.drawable.income),
                                contentDescription = "Saudi Riyal symbol",
                                modifier = Modifier.size(integerResource(id = R.integer.largeRiyalIconSize).dp),
                                tint = MaterialTheme.colorScheme.primary,
                            )
                            //icon to edit the balance
                            IconButton(
                                onClick = { showBalanceEditor.value = true }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.BorderColor,
                                    contentDescription = "Editing icon",
                                    tint = MaterialTheme.colorScheme.primary,
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