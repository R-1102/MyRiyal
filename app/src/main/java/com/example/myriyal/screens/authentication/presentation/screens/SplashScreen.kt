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
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Screen.LogIn.route) {
            popUpTo(Screen.LogIn.route) { inclusive = true } // prevent going back to splash
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ThemedLogo(
            modifier = Modifier
                .padding(top = integerResource(id= R.integer.logoLargeSpace).dp)
                .align(Alignment.Center)
        )
    }
}
