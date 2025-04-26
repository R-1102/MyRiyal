package com.example.myriyal.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
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
import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.core.local.enums.CategoryType
import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.ui.theme.Expense
import com.example.myriyal.ui.theme.Income

@Composable
fun RecordItemCardSimple(
    record: RecordEntity,
    category: CategoryEntity,
) {
    val categoryIcon = category.icon ?: ""
    val categoryType = category.type
    val recordAmount = record.amount
    val isExpense = categoryType == CategoryType.EXPENSE
    val riyalExpense = R.drawable.expenes
    val riyalIncome = R.drawable.income
    val riyalIcon =
        painterResource(id = if (isExpense) riyalExpense else riyalIncome)

    // Attempt to parse the category's color; fallback to theme color on failure
    val parsedCategoryColor = try {
        Color(android.graphics.Color.parseColor(category.color))
    } catch (e: IllegalArgumentException) {
        MaterialTheme.colorScheme.primary
    }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = integerResource(id = R.integer.extraSmallSpace).dp)
            .height(integerResource(id = R.integer.homeLastRecordCardHeight).dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,

            ),
        shape = RoundedCornerShape(integerResource(id = R.integer.roundedCornerShape).dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = (integerResource(id = R.integer.recordItemCardElevation)).dp
        ),
        border = BorderStroke(
            integerResource(id = R.integer.borderStroke).dp,
            parsedCategoryColor,
        ),

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal =  integerResource(id = R.integer.mediumSpace).dp)
        ) {
            Row{
                Text(
                    text = categoryIcon,
                    style = MaterialTheme.typography.titleLarge,
                )

                Spacer(modifier = Modifier.width(integerResource(id = R.integer.smallerSpace).dp))

                Text(
                    text = record.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
            Row {
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
        }
    }
}


