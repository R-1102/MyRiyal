package com.example.myriyal.screens.authentication.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myriyal.R
import com.example.myriyal.navigation.Screen
import com.example.myriyal.screens.profile.presentattion.vmModels.ProfileViewModel
import com.example.myriyal.ui.screenComponent.CustomCard
import com.example.myriyal.ui.screenComponent.CustomTextField
import com.example.myriyal.ui.screenComponent.GradientButton
import com.example.myriyal.ui.theme.ThemedLogo

@Composable
fun BalanceScreen(navController: NavHostController) {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val balance by profileViewModel.balance

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ThemedLogo(
            modifier = Modifier
                .padding(top = integerResource(id = R.integer.logoExtraLargeSpace).dp)
                .align(CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(integerResource(id = R.integer.logoMediumSpace).dp))

        CustomCard(
            modifier = Modifier
                .size(
                    width = integerResource(id = R.integer.cardWidth).dp,
                    height = integerResource(id = R.integer.cardHeightSmall).dp
                )
                .align(CenterHorizontally)
        ) {
            Column(
                modifier = Modifier
                    .padding((integerResource(id = R.integer.smallSpace).dp)),
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.Balance_header),
                    color = Color.Black,
                    fontSize = integerResource(id = R.integer.cardHeaderSize).sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        top = integerResource(id = R.integer.cardHeaderTopPadding).dp,
                        bottom = integerResource(id = R.integer.cardHeaderBottomPadding).dp
                    )
                )
                CustomTextField(
                    value =balance.toString(),
                    onValueChange = {/*balance = it*/ },
                    label = stringResource(id = R.string.enter_amount),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                Spacer(
                    modifier =
                    Modifier.height(integerResource(id = R.integer.buttonTextFieldSpace).dp)
                )

                GradientButton(
                    onClick = { navController.navigate(Screen.LogIn.route) },
                    text = stringResource(id = R.string.create_account)
                )
            }
        }
    }
}


