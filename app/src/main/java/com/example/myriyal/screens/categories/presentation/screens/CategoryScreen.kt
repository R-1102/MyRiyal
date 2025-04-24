package com.example.myriyal.screens.categories.presentation.screens

/*

// UI layer of the category feature.
// Displays the category list and a form for adding/editing categories.
//
// Data flows:
// - Reads data from: CategoryViewModel.categories (StateFlow)
// - Sends user input/actions to: CategoryViewModel.insert/update/delete/seed

@Composable
fun CategoryScreen(categoryViewModel: CategoryViewModel = hiltViewModel()) {

    // Collect category list as State from ViewModel
    val categories by categoryViewModel.categories.collectAsState()



 //    Trigger predefined category seeding on first composition
    LaunchedEffect(Unit) { // After i comment this block the recomposing rate became much less
        categoryViewModel.seedPredefinedCategories()
    }

    // Form state variables
    var selectedCategory by remember { mutableStateOf<CategoryEntity?>(null) }
    var name by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("#FF0000") }
    var icon by remember { mutableStateOf("🔥") }
    var status by remember { mutableStateOf(CategoryStatus.ACTIVE) }
    var type by remember { mutableStateOf(CategoryType.EXPENSE) }
    var isPredefined by remember { mutableStateOf(false) }

    // Resets form fields after submission
    fun resetForm() {
        selectedCategory = null
        name = ""
        color = "#FF0000"
        icon = "🔥"
        status = CategoryStatus.ACTIVE
        type = CategoryType.EXPENSE
        isPredefined = false
    }


    // Loads category fields into the form for editing
    fun loadCategoryForEdit(category: CategoryEntity) {
        selectedCategory = category
        name = category.name
        color = category.color
        icon = category.icon ?: ""
        status = category.status
        type = category.type
        isPredefined = category.isPredefined
    }

    // Main screen layout
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Reusable form Composable for adding/updating category
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
                    categoryViewModel.insert(category)
                } else {
                    categoryViewModel.update(category)
                }
                resetForm()
            }
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        // Category list section
        if (categories.isEmpty()) {
            Text("No categories found.")
        } else {
            LazyColumn {
               items(items = categories, key = { it.categoryId }) { category ->
                    CategoryItem(
                        category = category,
                        onEdit = { loadCategoryForEdit(category) },
                        onSoftDelete = { categoryViewModel.softDelete(category.categoryId) },
                        onDeleteForever = { categoryViewModel.delete(category) }
                    )
                }
            }
        }
    }
}



@Composable
fun CategoryForm(
    selectedCategory: CategoryEntity?, // Null = create mode, Non-null = edit mode
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
    onSubmit: () -> Unit // Action triggered when Add/Update button is clicked
) {
    // Input for category name
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Category Name") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Row: Input for color and icon (emoji)
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = color,
            onValueChange = onColorChange,
            label = { Text("Color") },
            modifier = Modifier.weight(1f) // Half width
        )
        OutlinedTextField(
            value = icon,
            onValueChange = onIconChange,
            label = { Text("Icon") },
            modifier = Modifier.weight(1f) // Half width
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Row: Dropdowns for status and type
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Uses generic dropdown composable (see below)
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

    // Checkbox to mark as predefined (seeded by app or created manually)
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = isPredefined,
            onCheckedChange = onPredefinedChange
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text("Predefined Category")
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Submit button. Label changes based on whether we're editing or creating
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
    selected: T, // Currently selected enum value
    options: List<T>, // Full enum list (e.g., CategoryStatus.values())
    onSelect: (T) -> Unit, // Callback when an option is chosen
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) } // Whether dropdown is open

    Column(modifier = modifier) {
        Text(text = label, style = MaterialTheme.typography.labelMedium)

        Box {
            // Read-only field shows the selected option
            OutlinedTextField(
                value = selected.name.lowercase().replaceFirstChar { it.uppercase() },
                onValueChange = {}, // No-op because it's read-only
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                }
            )

            // Dropdown with enum values as options
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
    onEdit: () -> Unit,           // When user clicks Edit
    onSoftDelete: () -> Unit,     // When user clicks "Deactivate"
    onDeleteForever: () -> Unit   // When user clicks "Delete"
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
                // Display category data
                Text("Name: ${category.name}", style = MaterialTheme.typography.bodyLarge)
                Text("Status: ${category.status}", style = MaterialTheme.typography.bodyMedium)
                Text("Type: ${category.type}", style = MaterialTheme.typography.bodySmall)
            }

            Column(horizontalAlignment = Alignment.End) {
                // Actions that modify the category through the ViewModel
                TextButton(onClick = onEdit) { Text("Edit") }
                TextButton(onClick = onSoftDelete) { Text("Deactivate") }
                TextButton(onClick = onDeleteForever) { Text("Delete") }

                // Shows a visual tag if this is a predefined category
                if (category.isPredefined) {
                    Text(
                        "⭐ Predefined",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

*/
