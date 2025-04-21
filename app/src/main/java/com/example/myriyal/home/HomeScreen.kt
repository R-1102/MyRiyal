package com.example.myriyal.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myriyal.R

@Composable
fun HomeScreen(navController: NavHostController) {
    val layoutDirection = LocalLayoutDirection.current

    LazyColumn(
        horizontalAlignment = Alignment.End,

        modifier = Modifier.padding(integerResource(id = R.integer.mediumSpace).dp)
    ) {
        item {
            //Balance Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(92.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),

                ) {
                Text(
                    text = stringResource(id = R.string.balance),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 18.dp, top = 32.dp)
                )


            }
        }

        item {
            // Chart Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(360.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),

            ) {
                Text(
                    text = stringResource(id = R.string.categoryChart),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 18.dp, top = 12.dp)
                )

            }

        }

        item {
            // "View More" with icon
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
                /*.clickable { navController.navigate("balance") }*/
            ) {
                Text(
                    text = stringResource(id = R.string.viewMore),
                    color = MaterialTheme.colorScheme.onPrimary,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                )
                Icon(
                    //if the language is English the Arrow will be Right (left for Arabic language)
                    imageVector = if (layoutDirection == LayoutDirection.Ltr)
                        Icons.Filled.KeyboardDoubleArrowRight
                    else
                        Icons.Filled.KeyboardDoubleArrowLeft,
                    contentDescription = "View More",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }

        item {
            //Latest Record Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(215.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),

                ) {
                // Add Records here
                Text(
                    text = stringResource(id = R.string.latestRecords),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 18.dp, top = 12.dp)
                )

            }

        }

        item {
            // "View More" with icon
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable { navController.navigate("ViewRecord_Screen") }

            ) {
                Text(
                    text = stringResource(id = R.string.viewMore),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textDecoration = TextDecoration.Underline,
                )
                //if the language is English the Arrow will be Right (left for Arabic language)
                Icon(
                    imageVector = if (layoutDirection == LayoutDirection.Ltr)
                        Icons.Filled.KeyboardDoubleArrowRight
                    else
                        Icons.Filled.KeyboardDoubleArrowLeft,
                    contentDescription = "View More",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }

    }
}
