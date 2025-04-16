package com.example.myriyal.screens.records.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.core.local.enums.CategoryType
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
    val riyalExpense= R.drawable.expenes
    val riyalIncome= R.drawable.income

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = integerResource(id = R.integer.extraSmallSpace).dp)
            .shadow(integerResource(id = R.integer.cardShadow).dp)
            .clip(RoundedCornerShape(integerResource(id = R.integer.roundCardCornerShape).dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
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
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = record.name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(android.graphics.Color.parseColor(categoryColor))
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = if (isExpense) "-$recordAmount" else "+$recordAmount",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isExpense) Expense else Income
                    )
                    val riyalIcon = painterResource(id = if (isExpense) riyalExpense else riyalIncome)

                    Image(
                        painter =  riyalIcon,
                        contentDescription = "Riyal Icon",
                        modifier = Modifier.size(20.dp, 20.dp),
                    )
                }
                Row {
                    IconButton(onClick = onEdit) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = onDelete) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            record.description?.takeIf { it.isNotBlank() }?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Note: $it",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
