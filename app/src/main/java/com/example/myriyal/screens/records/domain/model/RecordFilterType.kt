package com.example.myriyal.screens.records.domain.model

/**
 * Enum class representing the available filters for viewing financial records.
 *
 * Used in: RecordViewModel to filter records based on selected time range.
 *
 * Values:
 * - DAY:    Show only today's records
 * - WEEK:   Show records from the current week
 * - MONTH:  Show records from the current month
 * - YEAR:   Show records from the current year
 * - ALL:    Show all records without filtering
 *
 * Flow:
 * UI (filter selection) → ViewModel.setFilter() → updates StateFlow → recomputes filtered records
 */
enum class RecordFilterType {
    DAY, WEEK, MONTH, YEAR, ALL
}
