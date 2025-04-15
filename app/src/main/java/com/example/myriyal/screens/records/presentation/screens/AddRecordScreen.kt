package com.example.myriyal.screens.records.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myriyal.R
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screenComponent.CategoryDropdownMenu
import com.example.myriyal.screenComponent.CustomCard
import com.example.myriyal.screenComponent.CustomTextField
import com.example.myriyal.screenComponent.DatePickerModal
import com.example.myriyal.screenComponent.GradientButton
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import com.example.myriyal.screens.records.presentation.vmModels.RecordViewModel
import java.text.DateFormat
import java.util.Date

@Composable
fun AddRecordScreen(
    viewModel: RecordViewModel, categoryViewModel: CategoryViewModel
) {
    // State from ViewModels
    val categories by categoryViewModel.categories.collectAsState()

    // Form states
    var selectedRecord by remember { mutableStateOf<RecordEntity?>(null) }
    var selectedCategory by remember { mutableStateOf<CategoryEntity?>(null) }
    var recordName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    var recordDate by remember { mutableStateOf<Long?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.padding(top = 100.dp))

        CustomCard(
            modifier = Modifier
                .size(width = (integerResource(id= R.integer.cardWidth)).dp, height = 590.dp)
                .align(CenterHorizontally),
        ) {
            Column(
                modifier = Modifier.padding((integerResource(id= R.integer.smallSpace)).dp),
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.addRecord),
                    fontSize = integerResource(id= R.integer.cardHeaderSize).sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = integerResource(id= R.integer.cardHeaderTopPadding).dp,
                                                bottom = integerResource(id= R.integer.cardHeaderBottomPadding).dp)
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
                    modifier = Modifier.height(integerResource(id= R.integer.recordDescription).dp),
                    singleLine = false
                )
                CategoryDropdownMenu(selectedCategory = selectedCategory,
                    categories = categories,
                    onCategorySelected = { selectedCategory = it })

                //tracker start date
                var showDatePicker by remember { mutableStateOf(false) }
                val formattedDate = recordDate?.let {
                    DateFormat.getDateInstance().format(Date(it))
                } ?: ""
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
                            modifier = Modifier.clickable { showDatePicker = true },
                        )
                    }
                )
                if (showDatePicker) {
                    DatePickerModal(onDateSelected = { millis ->
                        recordDate = millis
                        showDatePicker = false
                    }, onDismiss = { showDatePicker = false })
                }

                Spacer(modifier = Modifier.height((integerResource(id= R.integer.buttonTextFieldSpace)).dp))

                // Button for Add or Update
                GradientButton(
                    onClick = {
                        val timestamp = System.currentTimeMillis()
                        val record = RecordEntity(
                            recordId = selectedRecord?.recordId ?: 0,
                            name = recordName,
                            description = description,
                            amount = amount.toDoubleOrNull() ?: 0.0,
                            categoryId = selectedCategory?.categoryId ?: return@GradientButton,
                            date = recordDate ?: selectedRecord?.date ?: timestamp,
                            createdAt = selectedRecord?.createdAt ?: timestamp,
                            updatedAt = timestamp
                        )

                        if (selectedRecord == null) {
                            viewModel.insert(record)
                        } else {
                            viewModel.update(record)
                        }
                        // Reset form after action
                        recordName = ""
                        description = ""
                        amount = ""
                        recordDate = null
                        selectedCategory = null
                        selectedRecord = null
                    },
                    text = stringResource(id = R.string.addRecord),
                    //enabled = selectedCategory != null && recordName.isNotBlank() && amount.isNotBlank(),
                )
            }
        }
    }
}
