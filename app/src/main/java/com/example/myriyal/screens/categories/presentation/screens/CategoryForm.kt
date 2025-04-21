package com.example.myriyal.screens.categories.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myriyal.R
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.enums.CategoryStatus
import com.example.myriyal.core.local.enums.CategoryType
import com.example.myriyal.screenComponent.CancelButton
import com.example.myriyal.screenComponent.CustomTextField
import com.example.myriyal.screenComponent.DatePickerModal
import com.example.myriyal.screenComponent.GradientButton
import com.example.myriyal.screenComponent.CustomDropdown
import com.example.myriyal.screens.categories.presentation.components.iconsList
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import java.text.DateFormat
import java.util.Date

@Composable
fun CategoryForm(
    initialCategory: CategoryEntity?,
    onSubmit: (CategoryEntity) -> Unit,
    onDismiss: () -> Unit,
) {

    val viewModel: CategoryViewModel = hiltViewModel()
    val categoryColorController = rememberColorPickerController()

    LaunchedEffect(initialCategory) {
        if (initialCategory != null) {
            viewModel.categoryName.value = initialCategory.name
            viewModel.categoryType.value = initialCategory.type
            viewModel.categoryIcon = initialCategory.icon.toString()
//            viewModel.categoryBudgetAmount = initialCategory.categoryBudgetAmount
            viewModel.startDate = initialCategory.createdAt
//            viewModel.categoryColor.value =
//                Color(android.graphics.Color.parseColor(initialCategory.color))

        } else {
            viewModel.categoryName.value = ""
            viewModel.categoryType.value = CategoryType.EXPENSE
            viewModel.categoryIcon = "🔥"
//            viewModel.categoryBudgetAmount = 0.0
            viewModel.startDate = System.currentTimeMillis()
            // Default color
//            viewModel.categoryColor.value = Color.White
        }
    }

    fun resetForm() {
        viewModel.categoryName.value = ""
        viewModel.categoryType.value = CategoryType.EXPENSE
//        viewModel.categoryColor = "0xFFFFFF"
        viewModel.categoryIcon = ""
//        categoryBudget = 0.0
        viewModel.startDate = System.currentTimeMillis()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Text(
            text = if (initialCategory == null) stringResource(id = R.string.addCategory)
            else stringResource(id = R.string.updateCategory),
            fontSize = integerResource(id = R.integer.cardHeaderSize).sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(
                top = integerResource(id = R.integer.cardHeaderTopPadding).dp,
                bottom = integerResource(id = R.integer.cardHeaderBottomPadding).dp
            )
        )

        //category name
        CustomTextField(
            value = viewModel.categoryName.value,
            onValueChange = {/* categoryName = it*/viewModel.categoryName.value = it },
//                    onValueChange = viewModel::onCategoryNameChange,/*{ categoryName = it },*/
            label = stringResource(R.string.EnterCategoryName),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )
        Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

        //category type
        CustomDropdown(
            value = viewModel.categoryType.value.toString()
                .lowercase(), /*categoryType.toString().lowercase(),*/
            onValueChange = { viewModel.categoryType.value.toString().lowercase() },
            label = stringResource(R.string.categoryType),
//                    selected = categoryType,
            list = CategoryType.entries,
            onSelect = { viewModel.categoryType.value = it }
        )
        Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

        //category color selection
        val showDialog = remember { mutableStateOf(false) }
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
                    stringResource(R.string.ChooseColor),
                    color = Color.DarkGray,
                    textAlign = TextAlign.Start,
                )
                //a Circle to show the selected color
                Card(
                    modifier = Modifier
                        .size(integerResource(R.integer.colorSampleSize).dp),
                    colors = CardDefaults.cardColors(
                        containerColor = categoryColorController.selectedColor.value
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
                        title = stringResource(R.string.ChooseColor),
                        categoryColor = categoryColorController,
//                            onColorSelected = {
//                                    enteredColor: Color ->
//                                categoryColorController.selectedColor.value = enteredColor
//                                showDialog.value = false
//                            },
//                            onDismiss = {
//                                showDialog.value = false},
                    )
                }
            }
        }
        Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

        //Category icon
        CustomDropdown(
            value = viewModel.categoryIcon,/*categoryIcon.toString(),*/
            onValueChange = { viewModel.categoryIcon = it },/*viewModel::onCategoryIconChange,*/
            label = stringResource(R.string.SelectIcon),
            list = iconsList,
            onSelect = { viewModel.categoryIcon/*categoryIcon*/ = it },
        )
        Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

//                //budget amount (commented until we implement the tracker table)
//                CustomTextField(
//                    value = categoryBudgetAmount.toString(),
//                    onValueChange = {viewModel.categoryBudgetAmount.value=it},
//                    label = stringResource(R.string.EnterBudgetAmount),
////                    singleLine = true,
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
//                )
//                Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

        //tracker start date
        var showDatePicker by remember { mutableStateOf(false) }
        val formattedDate = viewModel.startDate?.let {
            DateFormat.getDateInstance().format(Date(it))
        } ?: ""
        CustomTextField(
            value = formattedDate,
            onValueChange = {},
            label = stringResource(R.string.StartingDate),
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
                    if (millis != null) {
                        viewModel.startDate = millis
                    }
                    showDatePicker = false
                },
                onDismiss = { showDatePicker = false }
            )
        }
        Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

        //save button
        GradientButton(
            onClick = {
                val timestamp = System.currentTimeMillis()
                val category = CategoryEntity(
                    categoryId = initialCategory?.categoryId ?: 0,
                    name = viewModel.categoryName.value,
                    color = String.format(
                        "#%06X",
                        0xFFFFFF and categoryColorController.selectedColor.value.toArgb()
                    ),
                    icon = viewModel.categoryIcon,
                    type = /*categoryType,*/viewModel.categoryType.value,
                    status = CategoryStatus.ACTIVE,
                    isPredefined = false,
                    createdAt = initialCategory?.createdAt ?: timestamp,
                    updatedAt = timestamp
                )

                if (initialCategory == null) viewModel.insert(category)
                else viewModel.update(category)
                onSubmit(category)

            },
            text = if (initialCategory == null) stringResource(id = R.string.addCategory) else stringResource(
                id = R.string.updateCategory
            ),
        )
        Spacer(modifier = Modifier.height(integerResource(id = R.integer.extraSmallSpace).dp))

        CancelButton(
            onClick = {
                resetForm()
                onDismiss()
            },
            text = stringResource(id = R.string.cancel)
        )
    }
}
