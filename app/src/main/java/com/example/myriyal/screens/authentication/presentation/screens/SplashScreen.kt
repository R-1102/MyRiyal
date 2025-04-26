package com.example.myriyal.screens.authentication.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myriyal.R
import com.example.myriyal.navigation.Screen
import com.example.myriyal.ui.theme.ThemedLogo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    firebaseAuth: FirebaseAuth
) {
    LaunchedEffect(Unit) {
        delay(2000)

        val user = firebaseAuth.currentUser
        if (user == null) {
            navController.navigate(Screen.LogIn.route) {
                popUpTo(Screen.SplashScreen.route) { inclusive = true }
            }
        } else {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.SplashScreen.route) { inclusive = true }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ThemedLogo(
            modifier = Modifier
                .padding(top = integerResource(id = R.integer.logoLargeSpace).dp)
                .align(Alignment.Center)
                .size(
                    integerResource(id = R.integer.splashScreenLogoSizeWidth).dp,
                    integerResource(id = R.integer.splashScreenLogoSizeHeight).dp
                ),
        )
    }
}
