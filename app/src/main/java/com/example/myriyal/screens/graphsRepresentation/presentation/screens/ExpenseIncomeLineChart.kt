package com.example.myriyal.screens.graphsRepresentation.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.example.myriyal.ui.theme.Expense
import com.example.myriyal.ui.theme.Income
import network.chaintech.cmpcharts.axis.AxisData
import network.chaintech.cmpcharts.common.components.Legends
import network.chaintech.cmpcharts.common.model.LegendLabel
import network.chaintech.cmpcharts.common.model.LegendsConfig
import network.chaintech.cmpcharts.common.model.Point
import network.chaintech.cmpcharts.common.ui.SelectionHighlightPoint
import network.chaintech.cmpcharts.common.ui.ShadowUnderLine
import network.chaintech.cmpcharts.ui.linechart.LineChart
import network.chaintech.cmpcharts.ui.linechart.model.Line
import network.chaintech.cmpcharts.ui.linechart.model.LineChartData
import network.chaintech.cmpcharts.ui.linechart.model.LinePlotData
import network.chaintech.cmpcharts.ui.linechart.model.LineStyle

@SuppressLint("SuspiciousIndentation")
@Composable
fun ExpenseIncomeLineChart() {
    //should be the list of total expenses amount for each day for the last 30 days
    val expense = List(10) {
        Point(
            it.toFloat(),
            (0..100).random().toFloat()
        )
    }
    val income = List(10) {
        Point(
            it.toFloat(),
            (0..100).random().toFloat()
        )
    }
    val textMeasurer = rememberTextMeasurer()
    val lines = listOf(
        Line(
            dataPoints = expense,
            lineStyle = LineStyle(color = Expense),
            selectionHighlightPoint = SelectionHighlightPoint(color = Expense),
//            selectionHighlightPopUp = SelectionHighlightPopUp(
//                backgroundColor = Expense,
//                labelColor = Color.White,
//                labelTypeface = FontWeight.Bold,
//                textMeasurer = textMeasurer,
//            ),
            shadowUnderLine = ShadowUnderLine(
                brush = Brush.verticalGradient(
                    colors = listOf(Expense.copy(alpha = 0.4f), Color.Transparent)
                ),
                alpha = 0.5f
            )
        ),
        Line(
            dataPoints = income,
            lineStyle = LineStyle(color = Income),
            selectionHighlightPoint = SelectionHighlightPoint(color = Income),
//            selectionHighlightPopUp = SelectionHighlightPopUp(
//                backgroundColor = Income,
//                labelColor = Color.White,
//                labelTypeface = FontWeight.Bold,
//                textMeasurer = textMeasurer,
//            ),
            shadowUnderLine = ShadowUnderLine(
                brush = Brush.verticalGradient(
                    colors = listOf(Income.copy(alpha = 0.4f), Color.Transparent)
                ),
                alpha = 0.5f
            )
        )
    )
    val xAxis = AxisData.Builder()
        .steps(9)//should be the number of the days which is the list size
        .labelData { i -> i.toString() }
        .build()
    val yAxis = AxisData.Builder()
        .steps(5)//should be between the minimum and maximum
        .labelData { i -> (i * 20).toString() }
        .build()

    val chartData = LineChartData(
        linePlotData = LinePlotData(lines = lines),
        xAxisData = xAxis,
        yAxisData = yAxis
    )
    Column(
        modifier = Modifier
            .padding(integerResource(R.integer.cardPadding).dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        if (expense.isEmpty() && income.isEmpty()) {
            Text("No data available yet", style = MaterialTheme.typography.bodyMedium)
        } else {
            LineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(integerResource(R.integer.chartHeight).dp),
                lineChartData = chartData,
            )
            Legends(
                modifier = Modifier
                    .fillMaxWidth(),
                legendsConfig = LegendsConfig(
                    legendLabelList = listOf(
                        LegendLabel(name = "Expense", color = Expense),
                        LegendLabel(name = "Income", color = Income)
                    ),
                    gridColumnCount = 2
                )
            )
        }

    }
}
