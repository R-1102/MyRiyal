package com.example.myriyal.screens.categories.presentation.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.automirrored.filled.TrendingUp
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myriyal.R
import com.example.myriyal.screens.categories.domian.model.CategoryType
import com.example.myriyal.screens.categories.domian.model.CategoryFilter
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import com.example.myriyal.ui.theme.White

@Composable
fun CategoryFilterDropdown(
    expanded: Boolean,
    onDismiss: () -> Unit,
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        containerColor = White,
        modifier = Modifier.size(
            integerResource(R.integer.menuWidth).dp,
            integerResource(R.integer.menuHeight).dp
        )
    ) {
        // Income filter
        DropdownMenuItem(
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                        contentDescription = "Arrow Up",
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        stringResource(R.string.income),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = integerResource(id = R.integer.smallerSpace).dp)
                    )
                }
            },
            onClick = {
                categoryViewModel.setFilter(CategoryFilter.ByType(CategoryType.INCOME))
                onDismiss()
            }
        )
        HorizontalDivider(color = Gray, thickness = 0.5.dp)

        // Expense filter
        DropdownMenuItem(
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.TrendingDown,
                        contentDescription = "Arrow Down",
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        stringResource(R.string.expense),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = integerResource(id = R.integer.smallerSpace).dp)
                    )
                }
            },
            onClick = {
                categoryViewModel.setFilter(CategoryFilter.ByType(CategoryType.EXPENSE))
                onDismiss()
            }
        )

        HorizontalDivider(color = Gray, thickness = 0.5.dp)

        // Show All Categories
        DropdownMenuItem(
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.List,
                        contentDescription = "Arrow Down",
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        stringResource(R.string.showAll),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = integerResource(id = R.integer.smallerSpace).dp)
                    )
                }
            },
            onClick = {
                categoryViewModel.setFilter(CategoryFilter.All)
                onDismiss()
            }
        )
    }
}
