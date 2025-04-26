package com.example.myriyal.screens.authentication.presentation.screens

import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myriyal.R
import com.example.myriyal.navigation.Screen
import com.example.myriyal.ui.screenComponent.CustomCard
import com.example.myriyal.ui.screenComponent.CustomTextField
import com.example.myriyal.ui.screenComponent.GradientButton
import com.example.myriyal.screens.authentication.presentation.vmModels.LogInVM
import com.example.myriyal.screens.authentication.presentation.vmModels.NotificationViewModel
import com.example.myriyal.screens.records.presentation.screens.ViewRecordScreen
import com.example.myriyal.ui.theme.ThemedLogo

@Composable
fun LoginScreen(navController: NavHostController) {
    //VM Object
    val viewModel: LogInVM = hiltViewModel()
    val notificationViewModel: NotificationViewModel = hiltViewModel()

    // Pass user input to VM
    val email = viewModel.email
    val password = viewModel.password

    var showPassword by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.allEventsFlow.collect { event ->
            when (event) {
                is LogInVM.AllEvents.Message -> {
                    // Show toast missing
                    Log.d("LoginScreen", event.message)
                }
                is LogInVM.AllEvents.ErrorCode -> {
                    // Show toast missing
                    Log.e("LoginScreen", "Error ${event.code}: ${event.erMsg}")
                }
                is LogInVM.AllEvents.Error -> {
                    Log.e("LoginScreen", "Error: ${event.error}")
                }
                is LogInVM.AllEvents.ShouldNavigate -> {
                    notificationViewModel.fetchFcmToken()

                    navController.navigate(Screen.ViewRecord.route) {
                        popUpTo(Screen.ViewRecord.route) { inclusive = true }
                    }
                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ThemedLogo(
            modifier = Modifier
                .padding(top = integerResource(id= R.integer.logoExtraLargeSpace).dp)
                .align(CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(integerResource(id= R.integer.logoSmallSpace).dp))

        CustomCard(
            modifier = Modifier
                .size(width = integerResource(id= R.integer.cardWidth).dp, height = integerResource(id= R.integer.cardHeightLogin).dp)
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
                    modifier = Modifier.padding(top = integerResource(id= R.integer.cardHeaderTopPadding).dp, bottom = integerResource(id= R.integer.cardHeaderBottomPadding).dp)
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(integerResource(id= R.integer.smallSpace).dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(id = R.string.forgot_password),
                        fontSize = integerResource(id= R.integer.smallText).sp,
                        color = Color(0xFFBE4A4A),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .clickable { navController.navigate(Screen.ForgotPass.route) }
                    )
                }
                GradientButton(
                    onClick = { viewModel.logInValidation() },
                    text = stringResource(id = R.string.Login)
                )
                Spacer(modifier = Modifier.height(integerResource(id= R.integer.extraSmallSpace).dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(integerResource(id= R.integer.smallSpace).dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.dont_have_account),
                        fontSize = integerResource(id= R.integer.smallText).sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = stringResource(id = R.string.Signup),
                        fontSize = integerResource(id= R.integer.smallText).sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.SignUp.route)
                        }
                    )
                }
            }
        }
    }
}

