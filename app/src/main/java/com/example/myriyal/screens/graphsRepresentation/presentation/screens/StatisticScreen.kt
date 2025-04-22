package com.example.myriyal.screens.graphsRepresentation.presentation.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.example.myriyal.screenComponent.CustomCard

@Composable
fun StatisticScreen() {

    LazyColumn(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(integerResource(id = R.integer.mediumSpace).dp)
    ) {

        item {
            //Expense v.s Income Graph
            ExpenseIncomeLineChart(title = stringResource(R.string.expenseIncomeGraph))
        }

        item {
            // Record Distribution Graph

            }

        item {
            // Monthly Spending Graph
//
        }
    }
}