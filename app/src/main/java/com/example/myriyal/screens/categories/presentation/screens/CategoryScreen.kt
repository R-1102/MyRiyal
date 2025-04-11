package com.example.myriyal.screens.categories.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.enums.CategoryStatus
import com.example.myriyal.core.local.enums.CategoryType
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import androidx.compose.material.icons.filled.Star

@Composable
fun CategoryScreen(viewModel: CategoryViewModel) {
    val categories by viewModel.categories.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.seedPredefinedCategories()
    }


    var selectedCategory by remember { mutableStateOf<CategoryEntity?>(null) }
    var name by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("#FF0000") }
    var icon by remember { mutableStateOf("🔥") }
    var status by remember { mutableStateOf(CategoryStatus.ACTIVE) }
    var type by remember { mutableStateOf(CategoryType.EXPENSE) }
    var isPredefined by remember { mutableStateOf(false) }

    fun resetForm() {
        selectedCategory = null
        name = ""
        color = "#FF0000"
        icon = "🔥"
        status = CategoryStatus.ACTIVE
        type = CategoryType.EXPENSE
        isPredefined = false
    }

    fun loadCategoryForEdit(category: CategoryEntity) {
        selectedCategory = category
        name = category.name
        color = category.color
        icon = category.icon ?: ""
        status = category.status
        type = category.type
        isPredefined = category.isPredefined
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        CategoryForm(
            selectedCategory = selectedCategory,
            name = name,
            onNameChange = { name = it },
            color = color,
            onColorChange = { color = it },
            icon = icon,
            onIconChange = { icon = it },
            status = status,
            onStatusChange = { status = it },
            type = type,
            onTypeChange = { type = it },
            isPredefined = isPredefined,
            onPredefinedChange = { isPredefined = it },
            onSubmit = {
                val timestamp = System.currentTimeMillis()
                val category = CategoryEntity(
                    categoryId = selectedCategory?.categoryId ?: 0,
                    name = name,
                    color = color,
                    icon = icon,
                    status = status,
                    type = type,
                    isPredefined = isPredefined,
                    createdAt = selectedCategory?.createdAt ?: timestamp,
                    updatedAt = timestamp
                )
                if (selectedCategory == null) {
                    viewModel.insert(category)
                } else {
                    viewModel.update(category)
                }
                resetForm()
            }
        )

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        if (categories.isEmpty()) {
            Text("No categories found.")
        } else {
            LazyColumn {
                items(categories) { category ->
                    CategoryItem(
                        category = category,
                        onEdit = { loadCategoryForEdit(category) },
                        onSoftDelete = { viewModel.softDelete(category.categoryId) },
                        onDeleteForever = { viewModel.delete(category) }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryForm(
    selectedCategory: CategoryEntity?,
    name: String,
    onNameChange: (String) -> Unit,
    color: String,
    onColorChange: (String) -> Unit,
    icon: String,
    onIconChange: (String) -> Unit,
    status: CategoryStatus,
    onStatusChange: (CategoryStatus) -> Unit,
    type: CategoryType,
    onTypeChange: (CategoryType) -> Unit,
    isPredefined: Boolean,
    onPredefinedChange: (Boolean) -> Unit,
    onSubmit: () -> Unit
) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Category Name") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = color,
            onValueChange = onColorChange,
            label = { Text("Color") },
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            value = icon,
            onValueChange = onIconChange,
            label = { Text("Icon") },
            modifier = Modifier.weight(1f)
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        DropdownMenuBox(
            label = "Status",
            selected = status,
            options = CategoryStatus.values().toList(),
            onSelect = onStatusChange,
            modifier = Modifier.weight(1f)
        )
        DropdownMenuBox(
            label = "Type",
            selected = type,
            options = CategoryType.values().toList(),
            onSelect = onTypeChange,
            modifier = Modifier.weight(1f)
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = isPredefined,
            onCheckedChange = onPredefinedChange
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text("Predefined Category")
    }

    Spacer(modifier = Modifier.height(8.dp))

    Button(
        onClick = onSubmit,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(if (selectedCategory == null) "Add Category" else "Update Category")
    }
}

@Composable
fun <T : Enum<T>> DropdownMenuBox(
    label: String,
    selected: T,
    options: List<T>,
    onSelect: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(text = label, style = MaterialTheme.typography.labelMedium)
        Box {
            OutlinedTextField(
                value = selected.name.lowercase().replaceFirstChar { it.uppercase() },
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(option.name.lowercase().replaceFirstChar { it.uppercase() })
                        },
                        onClick = {
                            onSelect(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: CategoryEntity,
    onEdit: () -> Unit,
    onSoftDelete: () -> Unit,
    onDeleteForever: () -> Unit
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
                Text("Name: ${category.name}", style = MaterialTheme.typography.bodyLarge)
                Text("Status: ${category.status}", style = MaterialTheme.typography.bodyMedium)
                Text("Type: ${category.type}", style = MaterialTheme.typography.bodySmall)
            }
            Column(horizontalAlignment = Alignment.End) {
                TextButton(onClick = onEdit) { Text("Edit") }
                TextButton(onClick = onSoftDelete) { Text("Deactivate") }
                TextButton(onClick = onDeleteForever) { Text("Delete") }
                if (category.isPredefined) {
                    Text("⭐ Predefined", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}
