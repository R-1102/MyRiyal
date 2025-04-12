package com.example.myriyal.screens.records.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.records.presentation.vmModels.RecordViewModel

@Composable
fun RecordScreen(viewModel: RecordViewModel) {
    val records by viewModel.records.collectAsState()

    var amount by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    // UI Form & Record List
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Input: Record Name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Record Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Input: Amount
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val record = RecordEntity(
                    name = name,
                    amount = amount.toDoubleOrNull() ?: 0.0,
                    categoryId = 1, // You can dynamically select category later
                    date = System.currentTimeMillis(),
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis()
                )
                viewModel.insert(record)
                name = ""
                amount = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Record")
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // List of Records
        LazyColumn {
            items(records) { record ->
                RecordItem(
                    record = record,
                    onDelete = { viewModel.delete(record) }
                )
            }
        }
    }
}

@Composable
fun RecordItem(record: RecordEntity, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Name: ${record.name}", style = MaterialTheme.typography.bodyLarge)
                Text("Amount: ${record.amount}", style = MaterialTheme.typography.bodyMedium)
            }
            TextButton(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}