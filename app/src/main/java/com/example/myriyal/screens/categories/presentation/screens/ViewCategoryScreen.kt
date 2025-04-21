package com.example.myriyal.screens.categories.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myriyal.R
import com.example.myriyal.screenComponent.CustomDialog
import com.example.myriyal.screenComponent.CustomFloatingActionButton
import com.example.myriyal.screenComponent.SearchField
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel

@Composable
fun ViewCategoryScreen(categoryViewModel: CategoryViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        categoryViewModel.seedPredefinedCategories()
    }

    val categories by categoryViewModel.categories.collectAsState()
    var isDropdownExpanded by remember { mutableStateOf(false) }

    val showCategoryFormDialog = remember { mutableStateOf(false) }
    if (showCategoryFormDialog.value) {
        CustomDialog(
            shouldShowDialog = showCategoryFormDialog,
            content = {
                CategoryForm(
                    initialCategory = categoryViewModel.selectedCategory.value,
                    onSubmit = {
                        categoryViewModel.selectedCategory.value = null
                        showCategoryFormDialog.value = false
                    },
                    onDismiss = {
                        categoryViewModel.selectedCategory.value = null
                        showCategoryFormDialog.value = false
                    },
                )
            }
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                end = integerResource(id = R.integer.mediumSpace).dp,
                start = integerResource(id = R.integer.mediumSpace).dp
            )
    ) {
        Column(
            Modifier.fillMaxWidth()
        ) {
            //filter and search Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = integerResource(id = R.integer.extraSmallSpace).dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                SearchField(
                    value = "" /*categoryViewModel.searchQuery.value*/,
                    onValueChange = {/* categoryViewModel.searchQuery.value = it */ },
                    modifier = Modifier
                        .weight(1f)
                )
                Box{
                    IconButton(
                        onClick = { isDropdownExpanded = true },
                        modifier = Modifier
                            .padding(start = integerResource(id = R.integer.smallerSpace).dp)

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter),
                            contentDescription = "Filter",
                            tint = MaterialTheme.colorScheme.onPrimary,

                            )
                    }

                    CategoryFilterDropdown(
                        expanded = isDropdownExpanded,
                        onDismiss = { isDropdownExpanded = false }
                    )
                }
            }
            // Category list section
            if (categories.isEmpty()) {
                Text(stringResource(R.string.noCategories))
            } else {
                LazyColumn {
                    items(
                        items = categories,
                        key = { it.categoryId }
                    )
                    { category ->
                        CategoryItemCard(
                            category = category,
                            onEdit = {
                                categoryViewModel.selectedCategory.value = category
                                showCategoryFormDialog.value = true
                            },
                            onSoftDelete = { categoryViewModel.softDelete(category.categoryId) },
                        )
                    }
                }
            }
        }

        CustomFloatingActionButton(
            onClick = { showCategoryFormDialog.value = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(integerResource(R.integer.padding).dp)
        )
    }
}
