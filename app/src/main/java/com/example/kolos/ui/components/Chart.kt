package com.example.kolos.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot

@Composable
fun Chart(prices: List<Double>) {
    val points = prices.mapIndexed { index, price ->
        DataPoint(index.toFloat(), price.toFloat())
    }

    val line = LinePlot.Line(
        dataPoints = points,
        connection = LinePlot.Connection(Color.Blue, strokeWidth = 2.dp),
        intersection = LinePlot.Intersection(Color.Red, radius = 4.dp)
    )

    LineGraph(
        plot = LinePlot(
            lines = listOf(line),
            isZoomAllowed = true
        ),
        modifier = Modifier.padding(20.dp)
    )

}