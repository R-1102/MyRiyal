package com.example.myriyal.navigation.topBar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.example.myriyal.ui.theme.Red
import com.example.myriyal.ui.theme.White

@Composable
fun ProfileDropdownMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        containerColor = White,
        modifier = Modifier
            .width(integerResource(id = R.integer.topBarMenuWidth).dp)
            .height(integerResource(id = R.integer.topBarMenuHeight).dp),
    ) {
        DropdownMenuItem(
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Logout",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        stringResource(R.string.profileInfo),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(integerResource(id = R.integer.extraSmallSpace).dp)
                    )
                }

            }, onClick = onProfileClick
        )

        HorizontalDivider(color = Gray, thickness = 0.5.dp)

        DropdownMenuItem(
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "Logout",
                        tint = Red
                    )

                    Text(
                        stringResource(R.string.logout),
                        color = Red,
                        modifier = Modifier.padding(start = integerResource(id = R.integer.extraSmallSpace).dp)
                    )
                }
            }, onClick = onLogoutClick
        )
    }
}

