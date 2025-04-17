package com.example.myriyal.screens.records.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myriyal.screens.records.domain.model.RecordFilterType
import com.example.myriyal.ui.theme.Beige

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
            .padding(8.dp),
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
                label = { Text(filter.name.lowercase().replaceFirstChar { it.uppercase() }) },
                colors = FilterChipDefaults.filterChipColors(
                    labelColor= Beige,
                    selectedContainerColor = Beige,
                    selectedLabelColor = Color.White,
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = false,
                    borderColor = Beige,
                    borderWidth = 2.dp
                ),
            )
        }
    }
}