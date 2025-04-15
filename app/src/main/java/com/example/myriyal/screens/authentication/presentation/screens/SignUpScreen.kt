package com.example.myriyal.screens.authentication.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.myriyal.screens.authentication.presentation.vmModels.SignUpVM
import com.example.myriyal.ui.theme.ThemedLogo
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myriyal.screenComponent.CustomCard
import com.example.myriyal.screenComponent.CustomTextField
import com.example.myriyal.screenComponent.GradientButton

@Composable
fun SignUpScreen(navController: NavHostController) {
    val viewModel: SignUpVM = hiltViewModel()

    val username = viewModel.username
    val email = viewModel.email
    val password = viewModel.password
    val confirmPassword = viewModel.confirmPassword
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ThemedLogo(
            modifier = Modifier
                .padding(top = integerResource(id= R.integer.logoLargeSpace).dp)
                .align(CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(integerResource(id= R.integer.smallLogoCardSpace).dp))

        CustomCard(
            modifier = Modifier
                .size(width = integerResource(id= R.integer.cardWidth).dp, height = integerResource(id= R.integer.cardHeightSignUp).dp)
                .align(CenterHorizontally),
        ) {
            Column(
                modifier = Modifier
                    .padding((integerResource(id= R.integer.smallSpace).dp)),
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.signUp_header),
                    color = Color.Black,
                    fontSize = integerResource(id= R.integer.cardHeaderSize).sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = integerResource(id= R.integer.cardHeaderTopPadding).dp, bottom = integerResource(id= R.integer.cardHeaderBottomPadding).dp)
                )
                CustomTextField(
                    value = username,
                    onValueChange = viewModel::onUsernameChange,
                    label = stringResource(id = R.string.username)
                )
                CustomTextField(
                    value = email,
                    onValueChange = viewModel::onEmailChange,
                    label = stringResource(id = R.string.email),
                )
                CustomTextField(
                    value = password,
                    onValueChange = viewModel::onPasswordChange,
                    label = stringResource(id = R.string.password),
                    isPassword = true,
                    showPassword = showPassword,
                    onTogglePasswordVisibility = { showPassword = !showPassword },
                )
                CustomTextField(
                    value = confirmPassword,
                    onValueChange = viewModel::onConfirmPassword,
                    label = stringResource(id = R.string.confirmPassword),
                    isPassword = true,
                    showPassword = showConfirmPassword,
                    onTogglePasswordVisibility = { showConfirmPassword = !showConfirmPassword },
                )
                GradientButton(
                    onClick = {
                        viewModel.signUpValidation(
                            username = username,
                            email = email,
                            password = password,
                            confirmPassword = confirmPassword
                        );
                        navController.navigate(Routes.BALANCE)
                    },
                    text = stringResource(id = R.string.Signup)
                )
            }
            Spacer(modifier = Modifier.padding(integerResource(id= R.integer.smallSpace).dp))

          Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(integerResource(id= R.integer.smallSpace).dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.already_have_account),
                    fontSize = integerResource(id= R.integer.smallText).sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = stringResource(id = R.string.Login),
                    fontSize = integerResource(id= R.integer.smallText).sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { navController.navigate(Routes.LOGIN) }
                )
            }
        }
    }
}


