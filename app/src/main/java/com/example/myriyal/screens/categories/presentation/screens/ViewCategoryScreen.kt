package com.example.myriyal.screens.categories.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myriyal.R
import com.example.myriyal.screenComponent.CustomDialog
import com.example.myriyal.screenComponent.CustomFloatingActionButton
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel

@Composable
fun ViewCategoryScreen(categoryViewModel: CategoryViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        categoryViewModel.seedPredefinedCategories()
    }

    val viewModel: CategoryViewModel = hiltViewModel()
    val categories by viewModel.categories.collectAsState()

    val showCategoryFormDialog = remember { mutableStateOf(false) }
    if(showCategoryFormDialog.value){
        CustomDialog(
            shouldShowDialog = showCategoryFormDialog,
            content = {
                CategoryForm(
                    initialCategory = viewModel.selectedCategory.value,
                    onSubmit = {
                        viewModel.selectedCategory.value = null
                        showCategoryFormDialog.value = false
                    },
                    onDismiss = {
                        viewModel.selectedCategory.value = null
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
            Modifier
                .fillMaxWidth()
        ) {
            // Category list section
            if (categories.isEmpty()) {
                Text(stringResource(R.string.noCategories))
            } else {
                LazyColumn {
                    items(items = categories, key = { it.categoryId }) { category ->
                        CategoryItemCard(
                            category = category,
                            onEdit = {
                                viewModel.selectedCategory.value = category
                                showCategoryFormDialog.value = true
                            },
                            onSoftDelete = { viewModel.softDelete(category.categoryId) },
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

