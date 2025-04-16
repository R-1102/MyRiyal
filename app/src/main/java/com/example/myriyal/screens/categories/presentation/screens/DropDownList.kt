//
//
//package com.example.myriyal.screens.categories.presentation.screens
//
//
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.ExposedDropdownMenuBox
//import androidx.compose.material3.ExposedDropdownMenuDefaults
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import com.example.myriyal.core.local.enums.CategoryType
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun <T> DropDownList(
//    label: String,
//    options: List<T>,
//    selectedOption: T,
//    onOptionSelected: @Composable (T) -> Unit,
//) {
//    var isExpanded by remember { mutableStateOf(false) }
//    ExposedDropdownMenuBox(
//        expanded = isExpanded,
//        onExpandedChange = { isExpanded = it }
//
//    ) {
//        OutlinedTextField(
//            value = selectedOption.toString(),
//            onValueChange = {},
//            label = { Text(text = label) },
//            readOnly = true,
//            trailingIcon = {
//                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
//            },
//            modifier = Modifier.menuAnchor(),
//        )
//        ExposedDropdownMenu(
//            expanded = isExpanded,
//            onDismissRequest = { isExpanded = false }
//        ) {
//            for (option in options) {
//                DropdownMenuItem(
//                    text = { itemContent(option)},
//                    onClick = {
//                        onOptionSelected(option)
//                        isExpanded = false
//                    }
//                )
//            }
//        }
//    }
//}