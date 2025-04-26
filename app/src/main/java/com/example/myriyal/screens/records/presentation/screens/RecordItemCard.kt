package com.example.myriyal.screens.records.presentation.screens

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.records.data.local.RecordEntity
import com.example.myriyal.screens.categories.domian.model.CategoryType
import com.example.myriyal.ui.theme.Black
import com.example.myriyal.ui.theme.Expense
import com.example.myriyal.ui.theme.Income

@Composable
fun RecordItemCard(
    record: RecordEntity,
    category: CategoryEntity,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    val categoryIcon = category.icon ?: ""
    val categoryColor = category.color
    val categoryType = category.type
    val recordAmount = record.amount
    val isExpense = categoryType == CategoryType.EXPENSE
    val riyalExpense = R.drawable.expenes
    val riyalIncome = R.drawable.income
    val riyalIcon =
        painterResource(id = if (isExpense) riyalExpense else riyalIncome)


    // Before using this variable it was causing a crash
    val parsedCategoryColor = try {
        Color(android.graphics.Color.parseColor(categoryColor))
    } catch (e: IllegalArgumentException) {
        MaterialTheme.colorScheme.primary // fallback color
    }
    OutlinedCard(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = integerResource(id = R.integer.extraSmallSpace).dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,

            ),
        shape = RoundedCornerShape(integerResource(id = R.integer.roundedCornerShape).dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = integerResource(id = R.integer.recordItemCardElevation).dp
        ),
        border = BorderStroke(
            integerResource(id = R.integer.borderStroke).dp,
            parsedCategoryColor
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
                        text = categoryIcon,
                        style = MaterialTheme.typography.titleLarge,
                    )

                    Spacer(modifier = Modifier.width(integerResource(id = R.integer.smallerSpace).dp))

                    Text(
                        text = record.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,

                        color = parsedCategoryColor //  It was causing a crash here

                    )

                    Spacer(modifier = Modifier.width(integerResource(id = R.integer.smallerSpace).dp))

                    Text(
                        text = if (isExpense) "-$recordAmount" else "+$recordAmount",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (isExpense) Expense else Income
                    )

                    Image(
                        painter = riyalIcon,
                        contentDescription = "Riyal Icon",
                        modifier = Modifier.size(
                            integerResource(id = R.integer.riyalIconSize).dp,
                            integerResource(id = R.integer.riyalIconSize).dp
                        ),
                    )
                }
                Row {
                    IconButton(onClick = onEdit) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    IconButton(onClick = onDelete) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }

            record.description?.takeIf { it.isNotBlank() }?.let {
                Text(
                    text = " $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = Black,
                    modifier = Modifier.padding(integerResource(id = R.integer.extraSmallSpace).dp)
                )
            }
        }
    }
}
