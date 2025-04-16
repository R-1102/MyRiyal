package com.example.myriyal.screens.records.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import com.example.myriyal.screens.records.domain.model.RecordFilterType
import com.example.myriyal.screens.records.presentation.vmModels.RecordViewModel
import com.example.myriyal.utils.provideRecordViewModel


/**
 * Main screen to display and manage financial records (expenses or income).
 *
 * Fetches:
 * - Categories from [CategoryViewModel]
 * - Records from [RecordViewModel], filtered by day/week/month/year/all
 *
 * Sends:
 * - Insert/update/delete actions back to [RecordViewModel]
 */
@Composable
fun RecordScreen(

) {
    val context = LocalContext.current

    val recordViewModel = provideRecordViewModel(context)
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    // Observe reactive states from ViewModels
    val records by recordViewModel.records.collectAsState()
    val categories by categoryViewModel.categories.collectAsState()
    val selectedFilter by recordViewModel.filter.collectAsState()

    // Form state (controlled locally in this composable)
    var selectedRecord by remember { mutableStateOf<RecordEntity?>(null) }
    var selectedCategory by remember { mutableStateOf<CategoryEntity?>(null) }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // --- Filter Selection Chips ---
        FilterSelector(
            selectedFilter = selectedFilter,
            onFilterSelected = { recordViewModel.setFilter(it) }
        )

        Spacer(modifier = Modifier.height(6.dp))

        // --- RECORD FORM ---

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Record Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = {
                if (it.all { char -> char.isDigit() || char == '.' }) amount = it
            },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Category dropdown (uses categoryViewModel)
        CategoryDropdownMenu(
            selectedCategory = selectedCategory,
            categories = categories,
            onCategorySelected = { selectedCategory = it }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Button to insert or update a record
        Button(
            onClick = {
                val timestamp = System.currentTimeMillis()
                val record = RecordEntity(
                    recordId = selectedRecord?.recordId ?: 0,
                    name = name,
                    description = description,
                    amount = amount.toDoubleOrNull() ?: 0.0,
                    categoryId = selectedCategory?.categoryId ?: return@Button,
                    date = selectedRecord?.date ?: timestamp,
                    createdAt = selectedRecord?.createdAt ?: timestamp,
                    updatedAt = timestamp
                )

                if (selectedRecord == null) recordViewModel.insert(record)
                else recordViewModel.update(record)

                // Clear form after submission
                name = ""
                description = ""
                amount = ""
                selectedCategory = null
                selectedRecord = null
            },
            enabled = selectedCategory != null && name.isNotBlank() && amount.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (selectedRecord == null) "Add Record" else "Update Record")
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

        // --- LIST OF RECORDS ---

        LazyColumn {
            items(records) { record ->

                RecordItem(
                    record = record,
                    onDelete = { recordViewModel.delete(record) },
                    onEdit = {
                        selectedRecord = record
                        name = record.name
                        description = record.description.orEmpty()
                        amount = record.amount.toString()
                        selectedCategory = categories.find { it.categoryId == record.categoryId }
                    }
                )
            }
        }
    }
}

/**
 * Filter options (Day, Week, Month, Year).
 * Clicking again on a selected filter will unselect it (showing all).
 */
@Composable
fun FilterSelector(
    selectedFilter: RecordFilterType,
    onFilterSelected: (RecordFilterType) -> Unit
) {
    val filterOptions = listOf(
        RecordFilterType.DAY,
        RecordFilterType.WEEK,
        RecordFilterType.MONTH,
        RecordFilterType.YEAR
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        filterOptions.forEach { filter ->
            FilterChip(
                selected = filter == selectedFilter,
                onClick = {
                    if (filter == selectedFilter) {
                        // Tapping again toggles back to "All"
                        onFilterSelected(RecordFilterType.ALL)
                    } else {
                        onFilterSelected(filter)
                    }
                },
                label = { Text(filter.name.lowercase().replaceFirstChar { it.uppercase() }) }
            )
        }
    }
}

/**
 * Category selector dropdown (used in form).
 * Data comes from: [CategoryViewModel]
 */
@Composable
fun CategoryDropdownMenu(
    selectedCategory: CategoryEntity?,
    categories: List<CategoryEntity>,
    onCategorySelected: (CategoryEntity) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedCategory?.name ?: "Select Category",
            onValueChange = {},
            readOnly = true,
            label = { Text("Category") },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Select Category")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.name) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}

/**
 * Displays a single record in the list.
 * Includes Edit and Delete options.
 */
@Composable
fun RecordItem(
    record: RecordEntity,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Name: ${record.name}", style = MaterialTheme.typography.bodyLarge)
                Text("Amount: ${record.amount}", style = MaterialTheme.typography.bodyMedium)
                record.description?.takeIf { it.isNotBlank() }?.let {
                    Text("Note: $it", style = MaterialTheme.typography.bodySmall)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                TextButton(onClick = onEdit) { Text("Edit") }
                TextButton(onClick = onDelete) { Text("Delete") }
            }
        }
    }
}
