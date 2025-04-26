package com.example.myriyal.screens.records.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myriyal.R
import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.records.data.local.RecordEntity
import com.example.myriyal.ui.screenComponent.CategoryDropdownMenu
import com.example.myriyal.ui.screenComponent.CancelButton
import com.example.myriyal.ui.screenComponent.CustomTextField
import com.example.myriyal.ui.screenComponent.DatePickerModal
import com.example.myriyal.ui.screenComponent.GradientButton
import com.example.myriyal.screens.records.presentation.vmModels.RecordViewModel
import java.text.DateFormat
import java.util.Date

@Composable
fun RecordFormScreen(
    initialRecord: RecordEntity? = null,
    categories: List<CategoryEntity>,
    onSubmit: (RecordEntity) -> Unit,
    onDismiss: () -> Unit
) {

    val recordViewModel: RecordViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        initialRecord?.let {
            recordViewModel.populateForm(it, categories)
        }
    }


    // Form state
    var recordName by recordViewModel.name
    var description by recordViewModel.description
    var amount by recordViewModel.amount
    var selectedCategory by recordViewModel.selectedCategory


    var recordDate by remember {
        mutableStateOf<Long?>(
            initialRecord?.date ?: System.currentTimeMillis()
        )
    }
    var showDatePicker by remember { mutableStateOf(false) }

    val formattedDate = recordDate?.let {
        DateFormat.getDateInstance().format(Date(it))
    } ?: ""


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (initialRecord == null) stringResource(id = R.string.addRecord)
            else stringResource(id = R.string.updateRecord),
            fontSize = integerResource(id = R.integer.cardHeaderSize).sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(
                top = integerResource(id = R.integer.cardHeaderTopPadding).dp,
                bottom = integerResource(id = R.integer.cardHeaderBottomPadding).dp
            )
        )
        CustomTextField(
            value = recordName,
            onValueChange = { recordName = it },
            label = stringResource(id = R.string.recordName)
        )
        CustomTextField(
            value = amount,
            onValueChange = { amount = it },
            label = stringResource(id = R.string.recordAmount),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        CustomTextField(
            value = description,
            onValueChange = { description = it },
            label = stringResource(id = R.string.recordDescription),
            modifier = Modifier.height(integerResource(id = R.integer.recordDescription).dp),
            singleLine = false
        )

        CategoryDropdownMenu(
            selectedCategory = selectedCategory,
            categories = categories,
            onCategorySelected = { selectedCategory = it }
        )

        CustomTextField(
            value = formattedDate,
            onValueChange = {},
            label = stringResource(id = R.string.recordDate),
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Pick date",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { showDatePicker = true }
                )
            }
        )
        if (showDatePicker) {
            DatePickerModal(
                onDateSelected = {
                    recordDate = it
                    showDatePicker = false
                },
                onDismiss = { showDatePicker = false }
            )
        }
        Spacer(modifier = Modifier.height(integerResource(id = R.integer.buttonTextFieldSpace).dp))

        GradientButton(
            onClick = {
                val timestamp = System.currentTimeMillis()
                val record = RecordEntity(
                    recordId = initialRecord?.recordId ?: 0,
                    name = recordName,
                    description = description,
                    amount = amount.toDoubleOrNull() ?: 0.0,
                    categoryId = selectedCategory?.categoryId ?: return@GradientButton,
                    date = recordDate ?: System.currentTimeMillis(),
                    createdAt = initialRecord?.createdAt ?: timestamp,
                    updatedAt = timestamp
                )

                if (initialRecord == null) recordViewModel.insert(record)
                else recordViewModel.update(record)
                onSubmit(record)
            },
            text = if (initialRecord == null) stringResource(id = R.string.addRecord) else  stringResource(id = R.string.updateRecord)
        )
        Spacer(modifier = Modifier.height(integerResource(id = R.integer.extraSmallSpace).dp))

        CancelButton(
            onClick = {
                recordViewModel.resetForm()
                onDismiss()
            },
            text = stringResource(id = R.string.cancel)
        )
    }
}

