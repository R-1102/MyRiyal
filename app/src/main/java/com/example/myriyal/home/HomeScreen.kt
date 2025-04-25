package com.example.myriyal.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myriyal.R
import com.example.myriyal.screenComponent.CustomCardHomeStatistic
import com.example.myriyal.screens.profile.presentattion.vmModels.ProfileViewModel
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import com.example.myriyal.screens.records.presentation.vmModels.RecordViewModel

@Composable
fun HomeScreen(navController: NavHostController) {

    val profileViewModel: ProfileViewModel = hiltViewModel()
    val recordViewModel: RecordViewModel = hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()

    val balance by profileViewModel.balance

    val categories by categoryViewModel.categories.collectAsState()
    val records by recordViewModel.records.collectAsState()
    val latestRecords = records.take(3)

    LazyColumn(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(horizontal = integerResource(id = R.integer.mediumSpace).dp)
    ) {
        item {
            //Balance Card
            CustomCardHomeStatistic(
                modifier = Modifier
                    .height(integerResource(id = R.integer.balanceCardHeight).dp),

                ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(integerResource(id = R.integer.mediumSpace).dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.balance),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "$balance",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onTertiary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = integerResource(id = R.integer.extraSmallSpace).dp)
                        )
                        // Display Riyal icon next to Balance
                        Image(
                            painter = painterResource(id = R.drawable.expenes),
                            contentDescription = "Riyal Icon",
                            modifier = Modifier.size(
                                integerResource(id = R.integer.largeRiyalIconSize).dp,
                                integerResource(id = R.integer.largeRiyalIconSize).dp
                            ),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary)
                        )

                    }
                }
            }
        }

        item {
            //Latest Record Card
            CustomCardHomeStatistic(
                modifier = Modifier
                    .height(integerResource(id = R.integer.homeRecordCardHeight).dp),
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.latestRecords),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(integerResource(id = R.integer.mediumSpace).dp)
                    )

                    Column(
                        modifier = Modifier.padding(horizontal = integerResource(id = R.integer.mediumSpace).dp)
                    ) {
                        latestRecords.forEach { record ->
                            val category =
                                categories.find { it.categoryId == record.categoryId }
                            if (category != null) {
                                RecordItemCardSimple(
                                    record = record,
                                    category = category
                                )
                                Spacer(modifier = Modifier.height(integerResource(id = R.integer.extraSmallSpace).dp))
                            }
                        }
                    }
                }
            }
        }

        item {
            // "View More" with icon
            ViewMoreComponent(
                onClick = { navController.navigate("ViewRecord_Screen") }
            )
        }

        item {
            // Chart Card
            CustomCardHomeStatistic(
                modifier = Modifier
                    .height(integerResource(id = R.integer.chartCardHeight).dp)
                    .padding(top = integerResource(id = R.integer.smallSpace).dp),
                ) {
                Text(
                    text = stringResource(id = R.string.categoryChart),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(integerResource(id = R.integer.mediumSpace).dp)
                )
            }
        }

        item {
            // "View More" with icon
            ViewMoreComponent(
                onClick = { navController.navigate("Statistic_Screen") }
            )
        }
    }
}

