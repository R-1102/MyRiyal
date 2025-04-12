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
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myriyal.R
import com.example.myriyal.screens.authentication.presentation.component.GradientButton
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import java.util.Date
import java.text.DateFormat


@Composable
fun AddCategory() {
    var categName by remember { mutableStateOf("") }
    var categType by remember { mutableStateOf("") }

    var selectedColor by remember { mutableStateOf(Color(0xFF6200EE)) }
    var categColor = rememberColorPickerController()
    var categIcon by remember { mutableStateOf("") }

    var categBudgAmount by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf<Long?>(null) }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {


                val context = LocalContext.current
                //category name
                OutlinedTextField(
                    value = categName,
                    onValueChange = { categName = it },
                    label = { Text(text = context.getString(R.string.enterCategName)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )
                Spacer(Modifier.height(16.dp))

                //category type
                val options = listOf("Option_1", "Option_2", "Option_3", "Option_4")
                DropDownList(
                    label = context.getString(R.string.enterCategType),
                    options = options,
                    selectedOption = categType,
                    onOptionSelected = { categType = it }
                )
                Spacer(Modifier.height(16.dp))

                //category color selection
                val showDialog = remember { mutableStateOf(false) }
                // Trigger
                OutlinedButton(
                    onClick = { showDialog.value = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent /*categColor.selectedColor.value*/
                    ),
                    shape = RoundedCornerShape(4.dp),
//                        .padding(64.dp)
                    modifier = Modifier
                        .width(280.dp)
                        .height(55.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            context.getString(R.string.chooseCategColor),
                            color = Color.DarkGray,
                            textAlign = TextAlign.Start,
//                        modifier = Modifier.fillMaxWidth()
                        )
                        //a Circle to show the selected color
                        Card(
                            modifier = Modifier
                                .size(30.dp),
                            colors = CardDefaults.cardColors(
                                containerColor =categColor.selectedColor.value
                            ),
                            shape = CircleShape,
                        ) {}
                    }
                }
                // Show the dialog when showDialog is true
                if (showDialog.value) {
                    Dialog(onDismissRequest = { showDialog.value = false }) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surface,
                            tonalElevation = 8.dp
                        ) {
                            ColorPicker(
                                title = context.getString(R.string.chooseCategColor),
                                categColor = categColor,
                                initialColor = selectedColor
                            )
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))

                //category icon
                val icons = listOf("icon_1", "icon_2", "icon_3", "icon_4")
                DropDownList(
                    options = icons,
                    label = context.getString(R.string.icon),
                    selectedOption = categIcon,
                    onOptionSelected = { categIcon = it },
                )
                Spacer(Modifier.height(16.dp))

                //budget amount
                OutlinedTextField(
                    value = categBudgAmount,
                    onValueChange = { categBudgAmount = it },
                    label = { Text(text = context.getString(R.string.enterCategBudget)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                )
                Spacer(Modifier.height(16.dp))

                //tracker start date
                var showDatePicker by remember { mutableStateOf(false) }
                val formattedDate = startDate?.let {
                    DateFormat.getDateInstance().format(Date(it))
                } ?: ""
                OutlinedTextField(
                    value = formattedDate,
                    onValueChange = {},
                    label = { Text(context.getString(R.string.startDate)) },
                    readOnly = true,
                    modifier = Modifier
                        .clickable { showDatePicker = true },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Pick date"
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
                GradientButton(text=context.getString(R.string.save),
                    onClick = { TODO() })
//                Spacer(Modifier.height(16.dp))
                GradientButton(text=context.getString(R.string.cancel),
                    onClick = { TODO() })
            }
        }
    }
}