package com.example.myriyal.screens.authentication.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.myriyal.navigation.Screen
import com.example.myriyal.screenComponent.CustomCard
import com.example.myriyal.screenComponent.CustomTextField
import com.example.myriyal.screenComponent.GradientButton
import com.example.myriyal.ui.theme.ThemedLogo

@Composable
fun NewPassword(navController: NavHostController) {
    var confirmPassword by remember { mutableStateOf("") } //need to be deleted
    var password by remember { mutableStateOf("") } //need to be deleted

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ThemedLogo(
            modifier = Modifier
                .padding(top = integerResource(id= R.integer.logoExtraLargeSpace).dp)
                .align(CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(integerResource(id= R.integer.largeSpace).dp))

        CustomCard(
            modifier = Modifier
                .size(width = integerResource(id= R.integer.cardWidth).dp, height = integerResource(id= R.integer.cardHeightNewPassword).dp)
                .align(CenterHorizontally),
        ) {
            Column(
                modifier = Modifier
                    .padding((integerResource(id= R.integer.smallSpace).dp)),
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.newPassword),
                    color = Color.Black,
                    fontSize = integerResource(id= R.integer.cardHeaderSize).sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = integerResource(id= R.integer.cardHeaderTopPadding).dp, bottom = integerResource(id= R.integer.cardHeaderBottomPadding).dp)
                )
                Spacer(modifier = Modifier.height(integerResource(id= R.integer.largeSpace).dp))

                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = stringResource(id = R.string.password),
                )
                CustomTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = stringResource(id = R.string.confirmPassword),
                )
                Spacer(modifier = Modifier.height(integerResource(id= R.integer.buttonTextFieldSpace).dp))

                GradientButton(
                    onClick = { navController.navigate(Screen.LogIn.route) },
                    text = stringResource(id = R.string.submit)
                )
            }
        }
    }
}


