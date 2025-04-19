package com.example.myriyal.navigation.bottomBar

import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myriyal.R

data class NavItem(
    val route: String,
    val iconRes: Int,
    val labelRes: Int,
)

val navItems = listOf(
    NavItem(
        route = "ViewRecord_Screen",
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
        route = "ViewRecord_Screen",
        iconRes = R.drawable.statistic,
        labelRes = R.string.statistic,
    )
)