package com.example.myriyal.navigation.bottomBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myriyal.R
import com.example.myriyal.ui.theme.Beige

@Composable
fun BottomNavigationBar(navController: NavHostController, currentRoute: String?) {

    val notSelectedColor = MaterialTheme.colorScheme.onSecondaryContainer

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.shadow(integerResource(id = R.integer.shadow).dp)
    ) {
        navItems.forEach { item ->
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate(item.route) },
                icon = {
                    Image(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = stringResource(id = item.labelRes),
                        colorFilter = ColorFilter.tint(
                            if (currentRoute == item.route) Beige else notSelectedColor
                        ),
                        modifier = Modifier.padding(top = integerResource(id = R.integer.iconTopPadding).dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.labelRes),
                        color = if (currentRoute == item.route) Beige else notSelectedColor,
                        modifier = Modifier.padding(bottom = integerResource(id = R.integer.labelBottomPadding).dp)
                    )
                }
            )
        }
    }
}