package com.example.myriyal.screens.graphsRepresentation.presentation.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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

        //Expense v.s Income Graph
        item {
            CustomCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
//                    .wrapContentSize(), // it is causing a crash
                    .height(420.dp),
//                shape = RoundedCornerShape(12.dp),
//                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),

                ) {
                Text(
                    text = stringResource(id = R.string.expenseIncomeGraph),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 18.dp, top = 12.dp)
                )
                ExpenseIncomeLineChart(/*title = stringResource(R.string.expenseIncomeGraph)*/)
            }
        }

        item {
            // Record Distribution Graph
            CustomCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
//                    .wrapContentSize(), // it is causing a crash
                    .height(560.dp),
//                shape = RoundedCornerShape(12.dp),
//                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),

                ) {
                Text(
                    text = stringResource(id = R.string.expenseIncomeGraph),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 18.dp, top = 12.dp)
                )
            }
        }

        item {
            // Monthly Spending Graph
            CustomCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
//                    .wrapContentSize(), // it is causing a crash
                    .height(560.dp),
//                shape = RoundedCornerShape(12.dp),
//                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),

                ) {
                Text(
                    text = stringResource(id = R.string.expenseIncomeGraph),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 18.dp, top = 12.dp)
                )
            }
        }
    }
}