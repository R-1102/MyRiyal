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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.example.myriyal.screens.categories.data.local.CategoryEntity

/**
 * Dropdown menu for selecting a record category.
 * Displays all categories and returns the selected one.
 */
@Composable
fun CategoryDropdownMenu(
    selectedCategory: CategoryEntity?,
    categories: List<CategoryEntity>,
    onCategorySelected: (CategoryEntity) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        CustomTextField(
            value = selectedCategory?.name ?: stringResource(id = R.string.selectCategory),
            onValueChange = {},
            readOnly = true,
            label = stringResource(id = R.string.recordCategory),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .size(integerResource(R.integer.dropDownWidth).dp, integerResource(R.integer.dropDownHeight).dp)
                .align(androidx.compose.ui.Alignment.Center)
        ) {
            categories.forEach { category ->
                DropdownMenuItem(text = {
                    Text(
                        category.name, color = MaterialTheme.colorScheme.onSurface
                    )
                }, onClick = {
                    onCategorySelected(category)
                    expanded = false
                })
            }
        }
    }
}



