package com.example.myriyal.screens.graphsRepresentation.presentation.screens

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.myriyal.ui.screenComponent.CustomCardHomeStatistic

@Composable
fun ViewStatisticScreen() {
    LazyColumn(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(horizontal = integerResource(id = R.integer.mediumSpace).dp)
    ) {
        item {
            CustomCardHomeStatistic(
                modifier = Modifier.height(integerResource(id = R.integer.chartCardHeight).dp)
            ) {
                Text(
                    text = stringResource(R.string.incomeVsExpenses),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(integerResource(id = R.integer.mediumSpace).dp)
                )
                ExpenseIncomeLineChart()
            }
        }
            item {
                CustomCardHomeStatistic(
                    modifier = Modifier.height(integerResource(id = R.integer.chartCardHeight).dp)
                ) {
                    Text(
                        text = stringResource(R.string.monthlySpending),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(integerResource(id = R.integer.mediumSpace).dp)
                    )
                }
            }

        item {
            CustomCardHomeStatistic(
                modifier = Modifier.height(integerResource(id = R.integer.chartCardHeight).dp)
            ) {
                Text(
                    text = stringResource(R.string.categoryChart),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(integerResource(id = R.integer.mediumSpace).dp)
                )
            }
        }
    }
}