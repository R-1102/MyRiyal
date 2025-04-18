package com.example.myriyal.screens.records.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myriyal.screenComponent.CustomDialog
import com.example.myriyal.screenComponent.CustomFloatingActionButton
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import com.example.myriyal.screens.records.presentation.vmModels.RecordViewModel


//need to move the logic to viewmodel
@Composable
fun ViewRecordScreen() {

    val recordViewModel : RecordViewModel = hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    // Observe reactive states from ViewModels
    val records by recordViewModel.records.collectAsState()
    val categories by categoryViewModel.categories.collectAsState()

    // Form state (controlled locally in this composable)
    val selectedFilter by recordViewModel.filter.collectAsState()

    val shouldShowDialog = remember { mutableStateOf(false) } // 1

    if (shouldShowDialog.value) {
        CustomDialog(
            shouldShowDialog = shouldShowDialog,
            content = {
                RecordFormScreen(
                    initialRecord = recordViewModel.selectedRecord.value,
                    categories = categories,
                    onSubmit = {
                        recordViewModel.selectedRecord.value = null
                        shouldShowDialog.value = false
                    },
                    onDismiss = {
                        recordViewModel.selectedRecord.value = null
                        shouldShowDialog.value = false
                    },
                )
            }
        )
    }

    Column(
        Modifier.fillMaxWidth()
            .padding(top=100.dp,),//to be deleted
    ) {
        FilterSelector(
            selectedFilter = selectedFilter,
            onFilterSelected = { recordViewModel.setFilter(it) }
        )
        LazyColumn {
            items(records) { record ->
                val category = categories.find { it.categoryId == record.categoryId }
                if (category != null) {
                    RecordItemCard(
                        record = record,
                        category = category,
                        onDelete = { recordViewModel.delete(record) },
                        onEdit = {
                            recordViewModel.selectedRecord.value = record
                            shouldShowDialog.value = true
                        }
                    )
                }
            }
        }
        CustomFloatingActionButton(
            onClick = { shouldShowDialog.value = true },
            modifier = Modifier
                .align(Alignment.End)
                .padding(bottom = 60.dp) //Will be deleted

        )
    }

}

