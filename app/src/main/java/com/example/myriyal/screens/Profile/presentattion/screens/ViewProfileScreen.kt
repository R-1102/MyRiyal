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
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myriyal.R
import com.example.myriyal.screenComponent.CustomCard
import com.example.myriyal.screenComponent.CustomTextField

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
            modifier = Modifier.fillMaxWidth()
                .size(integerResource(R.integer.profileIconSize).dp),
            tint = MaterialTheme.colorScheme.secondary,
        )
        Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))
        CustomCard (
            modifier = Modifier.padding(integerResource(R.integer.padding).dp),
        ){
            var showNameEditor by remember { mutableStateOf(false) }
            var showBalanceEditor by remember { mutableStateOf(false) }

            //We need to use VM after implementing it instead of remember
            var userName by remember{ mutableStateOf("") }
            var balance by remember{ mutableDoubleStateOf(0.0) }

            val context = LocalContext.current
            //Row for the user name
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(integerResource(R.integer.padding).dp)

            ){
                Text(context.getString(R.string.userName),
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
                            onClick = { showNameEditor = true }
                        ){
                            Icon(
                                imageVector = Icons.Filled.BorderColor,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                            )
                        }
                    }
                )
                if(showNameEditor){
                    EditName(
                        onNameEntered = {enteredName:String ->
                            userName = enteredName
                            showNameEditor = false
                        },
                        onDismiss = {showNameEditor = false}
                    )
                }
            }

            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(integerResource(R.integer.padding).dp),
            ){
                Text(context.getString(R.string.balance),
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
                    //icon to edit the balance
                    trailingIcon = {
                        IconButton(
                            onClick = { showBalanceEditor = true }
                        ){
                            Icon(
                                imageVector = Icons.Filled.BorderColor,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                            )
                        }
                    }
                )
                if(showBalanceEditor){
                    EditBalance(
                        onBalanceEntered = {enteredBalance:Double ->
                            balance = enteredBalance
                            showNameEditor = false
                        },
                        onDismiss = {showNameEditor = false}
                    )
                }
            }
        }
    }
}