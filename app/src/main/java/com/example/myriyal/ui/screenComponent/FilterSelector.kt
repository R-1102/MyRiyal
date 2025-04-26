package com.example.myriyal.ui.screenComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.example.myriyal.screens.records.domain.model.RecordFilterType
import com.example.myriyal.ui.theme.White

/**
 * Filter options (Day, Week, Month, Year).
 * Clicking again on a selected filter will unselect it (showing all).
 */
@Composable
fun FilterSelector(
    selectedFilter: RecordFilterType,
    onFilterSelected: (RecordFilterType) -> Unit
) {
    val filterOptions = listOf(
        RecordFilterType.DAY,
        RecordFilterType.WEEK,
        RecordFilterType.MONTH,
        RecordFilterType.YEAR
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(integerResource(id = R.integer.smallerSpace).dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        filterOptions.forEach { filter ->
            FilterChip(
                selected = filter == selectedFilter,
                onClick = {
                    if (filter == selectedFilter) {
                        // Tapping again toggles back to "All"
                        onFilterSelected(RecordFilterType.ALL)
                    } else {
                        onFilterSelected(filter)
                    }
                },
                label = {
                    Text(
                        text = stringResource(
                            id = when (filter) {
                                RecordFilterType.DAY -> R.string.filterDay
                                RecordFilterType.WEEK -> R.string.filterWeek
                                RecordFilterType.MONTH -> R.string.filterMonth
                                RecordFilterType.YEAR -> R.string.filterYear
                                RecordFilterType.ALL -> R.string.filterAll
                            }
                        ),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    labelColor = MaterialTheme.colorScheme.onTertiary,
                    selectedContainerColor = MaterialTheme.colorScheme.onTertiary,
                    selectedLabelColor = White,
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = false,
                    borderColor = MaterialTheme.colorScheme.onTertiary,
                ),
                shape = RoundedCornerShape(integerResource(id = R.integer.filterChipCorner))
            )
        }
    }
}