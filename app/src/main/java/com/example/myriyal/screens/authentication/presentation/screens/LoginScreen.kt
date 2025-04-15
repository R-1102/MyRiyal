package com.example.myriyal.screens.authentication.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myriyal.R
import com.example.myriyal.navigation.Routes
import com.example.myriyal.screenComponent.CustomCard
import com.example.myriyal.screenComponent.CustomTextField
import com.example.myriyal.screenComponent.GradientButton
import com.example.myriyal.ui.theme.ThemedLogo

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") } //need to be deleted
    var password by remember { mutableStateOf("") } //need to be deleted
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ThemedLogo(
            modifier = Modifier
                .padding(top = 80.dp)
                .align(CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(20.dp))

        CustomCard(
            modifier = Modifier
                .size(width = 330.dp, height = 410.dp)
                .align(CenterHorizontally)
        ) {
            Column(
                modifier = Modifier
                    .padding(integerResource(id= R.integer.smallSpace).dp),
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.Login),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = integerResource(id= R.integer.cardHeaderSize).sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp, bottom = 30.dp)
                )

                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = stringResource(id = R.string.email),
                )
                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = stringResource(id = R.string.password),
                    isPassword = true,
                    showPassword = showPassword,
                    onTogglePasswordVisibility = { showPassword = !showPassword },
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(id = R.string.forgot_password),
                        fontSize = 15.sp,
                        color = Color(0xFFBE4A4A),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .clickable { navController.navigate(Routes.FORGOTPASSWORD) }
                    )
                }
                GradientButton(
                    onClick = { /* Handle Login */ },
                    text = stringResource(id = R.string.Login)
                )
                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.dont_have_account),
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = stringResource(id = R.string.Signup),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.clickable {
                            navController.navigate(Routes.SIGNUP)
                        }
                    )
                }
            }
        }
    }
}

