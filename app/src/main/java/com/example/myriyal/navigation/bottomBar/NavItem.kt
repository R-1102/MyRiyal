package com.example.myriyal.navigation.bottomBar

import com.example.myriyal.R

data class NavItem(
    val route: String,
    val iconRes: Int,
    val labelRes: Int,
)

val navItems = listOf(
    NavItem(
        route = "Home",
        iconRes = R.drawable.home,
        labelRes = R.string.home
    ),
    NavItem(
        route = "ViewRecord_Screen",
        iconRes = R.drawable.record,
        labelRes = R.string.records
    ),
    NavItem(
        route = "view_category",
        iconRes = R.drawable.categories,
        labelRes = R.string.categories
    ),
    NavItem(
        route = "Statistic",
        iconRes = R.drawable.statistic,
        labelRes = R.string.statistic,
    )
)