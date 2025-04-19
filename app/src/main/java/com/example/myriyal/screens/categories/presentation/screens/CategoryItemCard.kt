package com.example.myriyal.screens.categories.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myriyal.R
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.screens.categories.presentation.vmModels.CategoryViewModel
import com.example.myriyal.ui.theme.Black

@Composable
fun CategoryItemCard(
    category: CategoryEntity,
    onEdit: () -> Unit, // When user clicks Edit
    onSoftDelete: () -> Unit,     // When user clicks "Delete Button"
) {
    val viewModel: CategoryViewModel = hiltViewModel()
    val categoryBudgetAmount = viewModel.categoryBudgetAmount

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = integerResource(id = R.integer.extraSmallSpace).dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        shape = RoundedCornerShape(integerResource(id = R.integer.customCardRound).dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = integerResource(id = R.integer.recordItemCardElevation).dp
        ),
        border = BorderStroke(
            integerResource(id = R.integer.borderStroke).dp,
            Color(android.graphics.Color.parseColor(category.color))
        ),

        ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(integerResource(id = R.integer.smallSpace).dp),
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = category.icon ?: "",
                        style = MaterialTheme.typography.headlineMedium,
                    )

                    Spacer(modifier = Modifier.width(integerResource(id = R.integer.smallerSpace).dp))

                    Column(
                        modifier = Modifier.padding(start = integerResource(id = R.integer.extraSmallSpace).dp)

                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text(
                                text = category.name,
                                fontWeight = FontWeight.SemiBold,
                                color = Black
                            )
                            // Shows a visual tag if this is a predefined category
                            if (category.isPredefined) {
                                Text(
                                    "  ⭐",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text(
                                //if Category have a budget show it -- need if condition --
                                text = "${categoryBudgetAmount.toInt()}/${categoryBudgetAmount.toInt()} ",
                                color = Black,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Image(
                                painter = painterResource(id = R.drawable.expenes),
                                contentDescription = "Riyal Icon",
                                modifier = Modifier.size(
                                    integerResource(id = R.integer.riyalIconSize).dp,
                                    integerResource(id = R.integer.riyalIconSize).dp
                                ),
                                colorFilter = ColorFilter.tint(Black)
                            )
                        }

                    }

                }
                Spacer(modifier = Modifier.width(integerResource(id = R.integer.smallerSpace).dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    IconButton(onClick = onEdit) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit",
                            tint = Black
                        )
                    }

                    IconButton(onClick = onSoftDelete) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete",
                            tint = Black
                        )
                    }

                }
            }
        }
    }
}



