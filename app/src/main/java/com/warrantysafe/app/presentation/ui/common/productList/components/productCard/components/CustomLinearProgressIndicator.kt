package com.warrantysafe.app.presentation.ui.common.productList.components.productCard.components

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap

@Composable
fun CustomLinearProgressIndicator(
    progress: Float,
    modifier: Modifier,
    trackColor: Color,
    progressColor: Color ,
    strokeWidth: Float ,
    gapSize: Float
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val progressWidth = (progress.coerceIn(0f, 1f)) * width

        // Draw track
        drawLine(
            color = trackColor,
            start = Offset(0f, height / 2),
            end = Offset(width, height / 2),
            strokeWidth = strokeWidth
        )

        // Draw progress
        drawLine(
            color = progressColor,
            start = Offset(0f, height / 2),
            end = Offset(progressWidth - gapSize, height / 2), // Adjust for gap
            strokeWidth = strokeWidth,
            cap = StrokeCap.Square
        )
    }
}