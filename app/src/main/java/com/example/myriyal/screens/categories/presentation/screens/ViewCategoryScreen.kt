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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myriyal.R
import com.example.myriyal.screenComponent.CustomFloatingActionButton
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel

@Composable
fun ViewCategoryScreen() {

    val viewModel: CategoryViewModel = hiltViewModel()
    val categories by viewModel.categories.collectAsState()

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
                .padding(top = 100.dp),//to be deleted - mocking Top bar
        ) {
            // Category list section
            if (categories.isEmpty()) {
                Text("No categories found.")
            } else {
                LazyColumn {
                    items(items = categories, key = { it.categoryId }) { category ->
                        CategoryItemCard(
                            category = category,
                            onEdit = { TODO() },
                            onSoftDelete = { viewModel.softDelete(category.categoryId) },
                        )
                    }
                }
            }
        }

        CustomFloatingActionButton(
            onClick = { /*shouldShowDialog.value = true*/ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )
    }
}

