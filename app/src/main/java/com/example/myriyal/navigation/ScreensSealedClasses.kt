package com.example.myriyal.navigation

sealed class Screen(val route: String) {

    object SplashScreen : Screen ("Splash_screen")
    object LogIn : Screen ("Login_screen")
    object ForgotPass : Screen ("ForgotPass_Screen")
    object NewPass : Screen ("NewPass_Screen")
    object SignUp : Screen("SignUp_Screen")
    object Balance : Screen("Balance")
    object ViewCategory : Screen ("view_category")
    object Record : Screen("record_screen")
    object ViewRecord : Screen ("ViewRecord_Screen")
    object ViewProfile : Screen ("view_profile")
    object Home : Screen ("Home_Screen")
    object Statistic : Screen ("Statistic")

}