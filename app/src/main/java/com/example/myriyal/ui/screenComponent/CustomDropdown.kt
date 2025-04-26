package com.example.myriyal.ui.screenComponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import com.example.myriyal.R

@Composable
fun <T> CustomDropdown(
    value: String,
    onValueChange: (T) -> Unit,
    label: String,
    list: List<T>,
    onSelect: (T) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        CustomTextField(
            value = value,
            onValueChange = { onValueChange },
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
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .size(
                    integerResource(R.integer.dropDownWidth).dp,
                    integerResource(R.integer.dropDownHeight).dp
                )
                .align(androidx.compose.ui.Alignment.Center)
        ) {
            list.forEach { Item ->
                DropdownMenuItem(text = {
                    Text(
                        Item.toString(), color = MaterialTheme.colorScheme.onSurface
                    )
                },
                    onClick = {
                        onSelect(Item)
                        expanded = false
                    }
                )
            }
        }
    }
}
