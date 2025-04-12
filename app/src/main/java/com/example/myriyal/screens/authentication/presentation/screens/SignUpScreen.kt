package com.example.myriyal.screens.authentication.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myriyal.R
import com.example.myriyal.navigation.Routes
import com.example.myriyal.screens.authentication.presentation.component.CustomTextField
import com.example.myriyal.screens.authentication.presentation.component.GradientButton
import com.example.myriyal.ui.theme.ThemedLogo

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SignUpScreen( navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }
    var showConfirmPassword by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ThemedLogo(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .align(CenterHorizontally)
                    .size(74.dp, 94.dp),
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Card(
                modifier = Modifier
                    .size(width = 330.dp, height = 580.dp)
                    .padding(10.dp)
                    .align(CenterHorizontally)
                    .shadow(30.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.S1_header),
                        color = Color.Black,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top= 10.dp,bottom = 20.dp)
                    )

                    CustomTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = stringResource(id = R.string.username)
                    )

                    CustomTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = stringResource(id = R.string.email),
                    )

                    CustomTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = stringResource(id = R.string.phonenumber),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    CustomTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = stringResource(id = R.string.password),
                        isPassword = true,
                        showPassword = showPassword,
                        onTogglePasswordVisibility = { showPassword = !showPassword },
                    )

                    CustomTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = stringResource(id = R.string.confirmpassword),
                        isPassword = true,
                        showPassword = showConfirmPassword,
                        onTogglePasswordVisibility = { showConfirmPassword = !showConfirmPassword },
                    )
                    GradientButton(
                        onClick = { /* Handle sign up */ },
                        text = stringResource(id = R.string.Signup)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.already_have_account),
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = stringResource(id = R.string.Login),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF005430),
                            modifier = Modifier.clickable { navController.navigate(Routes.LOGIN)}
                        )
                    }
                }
            }
        }
    }
}



