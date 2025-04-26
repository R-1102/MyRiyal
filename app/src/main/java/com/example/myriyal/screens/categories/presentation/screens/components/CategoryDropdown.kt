package com.example.myriyal.screens.categories.presentation.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.example.myriyal.screens.categories.domian.model.CategoryType
import com.example.myriyal.ui.screenComponent.CustomTextField

@Composable
fun CategoryDropdown(
    value: String,
    onValueChange: (CategoryType) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        CustomTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            label = label,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            },
            modifier = modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.surface,
            modifier = Modifier.size(
                integerResource(R.integer.dropDownWidth).dp,
                integerResource(R.integer.categoryDropDownHeight).dp,
            )
        ) {
            // Income
            DropdownMenuItem(
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                            contentDescription = "Arrow Up",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = stringResource(R.string.income),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(start = integerResource(id = R.integer.smallerSpace).dp)
                        )
                    }
                },
                onClick = {
                    onValueChange(CategoryType.INCOME)
                    expanded = false
                }
            )

            HorizontalDivider(color = Gray, thickness = 0.5.dp)

            // Expense
            DropdownMenuItem(
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.TrendingDown,
                            contentDescription = "Arrow Down",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = stringResource(R.string.expense),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(start = integerResource(id = R.integer.smallerSpace).dp)
                        )
                    }
                },
                onClick = {
                    onValueChange(CategoryType.EXPENSE)
                    expanded = false
                }
            )
        }
    }
}
