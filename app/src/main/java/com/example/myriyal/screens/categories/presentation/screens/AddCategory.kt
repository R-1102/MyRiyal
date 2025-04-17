package com.example.myriyal.screens.categories.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myriyal.R
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.enums.CategoryStatus
import com.example.myriyal.core.local.enums.CategoryType
import com.example.myriyal.screenComponent.CustomCard
import com.example.myriyal.screenComponent.CustomTextField
import com.example.myriyal.screenComponent.DatePickerModal
import com.example.myriyal.screenComponent.GradientButton
import com.example.myriyal.screens.categories.presentation.components.CustomDropdown
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import java.text.DateFormat
import java.util.Date

@Composable
fun AddCategory() {
    val viewModel: CategoryViewModel = hiltViewModel()

    var categoryName = viewModel.categoryName
    var categoryType =viewModel.categoryType

    val categoryColorController = rememberColorPickerController()
    var categoryIcon = viewModel.categoryIcon

    var startDate= viewModel.startDate

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,

    ) {
        CustomCard(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .padding(integerResource(id = R.integer.columnPadding).dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {

                val context = LocalContext.current

                //category name
                CustomTextField(
                    value = categoryName.value,
                    onValueChange = {viewModel.categoryName.value=it},
//                    onValueChange = viewModel::onCategoryNameChange,/*{ categoryName = it },*/
                    label = context.getString(R.string.EnterCategoryName),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )
                Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

                //category type
                CustomDropdown(
                    value = categoryType.value.toString().lowercase(),
                    onValueChange = {viewModel.categoryType.value.toString().lowercase()},
                    label = context.getString(R.string.categoryType),
//                    selected = categoryType,
                    list = CategoryType.entries,
                    onSelect = { viewModel.categoryType.value = it }
                )
                Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

                //category color selection
                val showDialog = remember { mutableStateOf(false) }
                // Trigger
                OutlinedButton(
                    onClick = { showDialog.value = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(integerResource(R.integer.RoundedCornerShape).dp),
                    modifier = Modifier
                        .width(integerResource(R.integer.colorFieldWidth).dp)
                        .height(integerResource(R.integer.colorFieldHeight).dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            context.getString(R.string.ChooseColor),
                            color = Color.DarkGray,
                            textAlign = TextAlign.Start,
                        )
                        //a Circle to show the selected color
                        Card(
                            modifier = Modifier
                                .size(integerResource(R.integer.colorSampleSize).dp),
                            colors = CardDefaults.cardColors(
                                containerColor =categoryColorController.selectedColor.value
                            ),
                            shape = CircleShape,
                        ) {}
                    }
                }
                // Show the dialog when showDialog is true
                if (showDialog.value) {
                    Dialog(onDismissRequest = { showDialog.value = false }) {
                        Surface(
                            shape = RoundedCornerShape(integerResource(R.integer.RoundedCornerShape).dp),
                            color = MaterialTheme.colorScheme.surface,
                            tonalElevation = integerResource(R.integer.tonalElevation).dp
                        ) {
                            ColorPicker(
                                title = context.getString(R.string.ChooseColor),
                                categoryColor = categoryColorController,
                            )
                        }
                    }
                }
                Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

                CustomDropdown(
                    value = categoryIcon ,
                    onValueChange = viewModel::onCategoryIconChange,
                    label = context.getString(R.string.SelectIcon),
                    list = iconsList,
                    onSelect = {categoryIcon = it},
                )
                Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

//                //budget amount (commented until we implement the tracker table)
//                CustomTextField(
//                    value = categoryBudgetAmount.toString(),
//                    onValueChange = {viewModel.categoryBudgetAmount.value=it},
//                    label = context.getString(R.string.EnterBudgetAmount),
////                    singleLine = true,
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
//                )
//                Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

                //tracker start date
                var showDatePicker by remember { mutableStateOf(false) }
                val formattedDate = startDate?.let {
                    DateFormat.getDateInstance().format(Date(it))
                } ?: ""
                CustomTextField(
                    value = formattedDate,
                    onValueChange = {},
                    label = context.getString(R.string.StartingDate),
                    readOnly = true,
                    modifier = Modifier,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Pick date",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable { showDatePicker = true },
                        )
                    }
                )
                if (showDatePicker) {
                    DatePickerModal(
                        onDateSelected = { millis ->
                            startDate = millis
                            showDatePicker = false
                        },
                        onDismiss = { showDatePicker = false }
                    )
                }
                Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

                //save button
                GradientButton(text = context.getString(R.string.Save),
                    onClick = {
                        val timestamp = System.currentTimeMillis()
                        val newCategory = CategoryEntity(
                            name = viewModel.categoryName.value,
                            color = categoryColorController.selectedColor.value.toString(),
                            icon = categoryIcon,
                            type = viewModel.categoryType.value,
                            status = CategoryStatus.ACTIVE,
                            isPredefined = false,
                            createdAt = timestamp,
                            updatedAt = timestamp
                        )
                        viewModel.insert(newCategory)

                        // Reset after save
                        viewModel.categoryName.value = ""
                        viewModel.categoryIcon = ""
                    })
            }
        }
    }
}