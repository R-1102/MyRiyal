package com.example.myriyal.screens.authentication.presentation.screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NewPassword(navController: NavHostController) {
    var confirmPassword by remember { mutableStateOf("") } //need to be deleted
    var password by remember { mutableStateOf("") } //need to be deleted

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ThemedLogo(
            modifier = Modifier
                .padding(top = 70.dp)
                .align(CenterHorizontally)
                .size(74.dp, 94.dp),
        )

        Spacer(modifier = Modifier.padding(18.dp))

        CustomCard(
            modifier = Modifier
                .size(width = 330.dp, height = 340.dp)
                .align(CenterHorizontally),
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp),
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.newPassword),
                    color = Color.Black,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
                )

                Spacer(modifier = Modifier.height(18.dp))

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

                Spacer(modifier = Modifier.height(26.dp))

                GradientButton(
                    onClick = { navController.navigate(Routes.LOGIN) },
                    text = stringResource(id = R.string.submit)
                )

            }
        }
    }
}


