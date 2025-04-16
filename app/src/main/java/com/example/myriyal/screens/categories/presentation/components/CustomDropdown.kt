package com.example.myriyal.screens.categories.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myriyal.screenComponent.CustomTextField

@Composable
fun <T> CustomDropdown(
    value:String,
    onValueChange : (T)-> Unit,
    label: String,
    list: List<T>,
    onSelect: (T) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Box {
        CustomTextField/*OutlinedTextField*/(
            value = value,
            onValueChange = {onValueChange},
            readOnly = true,
            label = label,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
           list.forEach { Item->
                DropdownMenuItem(
                    text = { Item },
                    onClick = {
                        onSelect(Item)
                        expanded = false
                    }
                )
            }
        }

        // DropdownMenu updated version to set the max hit of the list but it's lead to a crash!
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            LazyColumn(
//                modifier = Modifier.heightIn(max = 200.dp) // adjust max height as needed
//            ) {
//                items(list) { item ->
//                    DropdownMenuItem(
//                        text = { item }, // assuming item has a `name` property
//                        onClick = {
//                            onSelect(item)
//                            expanded = false
//                        }
//                    )
//                }
//            }
//        }
    }
}
