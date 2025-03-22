package com.example.kolos.ui.components


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot

@SuppressLint("SuspiciousIndentation")
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
//            modifier = Modifier.padding(vertical = 50.dp, horizontal = 20.dp)
            modifier = Modifier.padding(bottom = 50.dp, start = 20.dp, end = 20.dp)

        )


}