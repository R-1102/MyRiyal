package com.example.myriyal.navigation.topBar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myriyal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    navController: NavHostController,
    currentRoute: String?,
    darkTheme: Boolean,
    toggleTheme: () -> Unit,
    onLogout: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        modifier = Modifier.padding(
            horizontal = integerResource(id = R.integer.extraSmallSpace).dp,
            vertical = integerResource(id = R.integer.mediumSpace).dp
        ),

        title = {
            Text(
                text = when (currentRoute) {
                    "ViewRecord_Screen" -> stringResource(R.string.records)
                    "view_category" -> stringResource(R.string.categories)
                    "view_profile" -> stringResource(R.string.profile)
                    "Home_Screen" -> stringResource(R.string.home)
                    "Statistic" -> stringResource(R.string.statistic)
                    else -> ""
                },
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
        },

        actions = {
            // Dark/Light toggle
            IconButton(onClick = toggleTheme) {
                Icon(
                    imageVector = if (darkTheme) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                    contentDescription = "Dark/Light Mode",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            // Profile dropdown
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            ProfileDropdownMenu(
                expanded = expanded,
                onDismiss = { expanded = false },
                onProfileClick = {
                    navController.navigate("view_profile")
                    expanded = false
                },
                onLogoutClick = {
                    onLogout()
                    expanded = false
                },
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}