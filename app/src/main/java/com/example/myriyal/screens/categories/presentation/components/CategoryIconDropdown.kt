package com.example.myriyal.screens.categories.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.myriyal.R

@Composable
fun CategoryIconDropdown(
    selectedIcon: String,
    onSelect: (String) -> Unit
) {
    val icons = listOf("🔥", "🍔", "🚗", "💡", "🏠", "💼")
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box {
        OutlinedTextField(
            value = selectedIcon,
            onValueChange = {},
            readOnly = true,
            label = { Text(context.getString(R.string.icon)) },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            icons.forEach { icon ->
                DropdownMenuItem(
                    text = { Text(icon) },
                    onClick = {
                        onSelect(icon)
                        expanded = false
                    }
                )
            }
        }
    }
}
