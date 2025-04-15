package com.example.myriyal.screens.categories.presentation.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myriyal.R
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.enums.CategoryStatus
import com.example.myriyal.core.local.enums.CategoryType
import com.example.myriyal.screenComponent.GradientButton
import com.example.myriyal.screens.categories.presentation.components.CategoryIconDropdown
import com.example.myriyal.screens.categories.presentation.components.CategoryTypeDropdown
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun AddCategory(viewModel: CategoryViewModel) {
    val context = LocalContext.current

    // --- Form State ---
    var categoryName by remember { mutableStateOf("") }
    var categoryType by remember { mutableStateOf(CategoryType.EXPENSE) }
    val categoryColor = rememberColorPickerController()
    var categoryIcon by remember { mutableStateOf("") }
    var showColorDialog by remember { mutableStateOf(false) }

    // --- Screen Layout ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // --- Category Name ---
        OutlinedTextField(
            value = categoryName,
            onValueChange = { categoryName = it },
            label = { Text(context.getString(R.string.EnterCategoryName)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // --- Category Type Dropdown ---
        CategoryTypeDropdown(
            selected = categoryType,
            onSelect = { categoryType = it }
        )

        Spacer(Modifier.height(16.dp))

        // --- Icon Dropdown ---
        CategoryIconDropdown(
            selectedIcon = categoryIcon,
            onSelect = { categoryIcon = it }
        )

        Spacer(Modifier.height(16.dp))

        // --- Color Picker Button ---
        OutlinedButton(
            onClick = { showColorDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Choose Color")
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = categoryColor.selectedColor.value
                    )
                ) {
                    Spacer(modifier = Modifier.size(30.dp)) // Sets circle size
                }
            }
        }

        // --- Color Picker Dialog ---
        if (showColorDialog) {
            Dialog(onDismissRequest = { showColorDialog = false }) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    tonalElevation = 8.dp
                ) {
                    ColorPicker(
                        title = "Choose Color",
                        categoryColor = categoryColor
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // --- Save Button ---
        GradientButton(
            text = "Save",
            onClick = {
                val timestamp = System.currentTimeMillis()
                val newCategory = CategoryEntity(
                    name = categoryName,
                    color = categoryColor.selectedColor.value.toString(),
                    icon = categoryIcon,
                    type = categoryType,
                    status = CategoryStatus.ACTIVE,
                    isPredefined = false,
                    createdAt = timestamp,
                    updatedAt = timestamp
                )
                viewModel.insert(newCategory)

                // Optional reset
                categoryName = ""
                categoryIcon = ""
            }
        )
    }
}
