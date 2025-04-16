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
import com.example.myriyal.screenComponent.CustomTextField

@Composable
fun <T> CustomDropdown(
    value:String,
    onValueChange : (T)-> Unit,
    label: String,
//    selected: String,
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
            label = label /*context.getString(R.string.categoryType)*/,
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
           list.forEach { Item/*type*/ ->

                DropdownMenuItem(
                    text = { Item/*type.name*/ },
                    onClick = {
                        onSelect(Item)
                        expanded = false
                    }
                )
            }
        }
    }
}
