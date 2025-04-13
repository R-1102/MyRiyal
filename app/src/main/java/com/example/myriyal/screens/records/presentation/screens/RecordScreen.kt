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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import com.example.myriyal.screens.records.presentation.vmModels.RecordViewModel

/**
 * Screen to display and manage financial records (income/expenses).
 *
 * Responsibilities:
 * - Displays a form to create or update a record
 * - Shows a list of all existing records
 * - Allows editing or deleting records
 *
 * Data Flow:
 * - Fetches category list from: CategoryViewModel → categoryRepository → Room DAO
 * - Fetches records from: RecordViewModel → recordRepository → Room DAO
 * - Sends insert/update/delete actions to: RecordViewModel → UseCases → Repository
 */
@Composable
fun RecordScreen(
    viewModel: RecordViewModel,
    categoryViewModel: CategoryViewModel
) {
    // State from ViewModels
    val records by viewModel.records.collectAsState()
    val categories by categoryViewModel.categories.collectAsState()

    // Form states
    var selectedRecord by remember { mutableStateOf<RecordEntity?>(null) }
    var selectedCategory by remember { mutableStateOf<CategoryEntity?>(null) }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // --- FORM ---

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Record Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = {
                if (it.all { char -> char.isDigit() || char == '.' }) amount = it
            },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        CategoryDropdownMenu(
            selectedCategory = selectedCategory,
            categories = categories,
            onCategorySelected = { selectedCategory = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Button for Add or Update
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

                if (selectedRecord == null) {
                    viewModel.insert(record)
                } else {
                    viewModel.update(record)
                }

                // Reset form after action
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

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // --- RECORD LIST ---

        LazyColumn {
            items(records) { record ->
                RecordItem(
                    record = record,
                    onDelete = { viewModel.delete(record) },
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
 * Represents a single Record item in the list.
 * Includes Edit and Delete actions.
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
                TextButton(onClick = onEdit) {
                    Text("Edit")
                }
                TextButton(onClick = onDelete) {
                    Text("Delete")
                }
            }
        }
    }
}
