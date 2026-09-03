package com.example.project2_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project2_ui.theme.*

@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
    trailing: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(title, style = MaterialTheme.typography.titleLarge, color = InkNavy)
            if (subtitle != null) {
                Spacer(Modifier.height(2.dp))
                Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MutedText)
            }
        }
        trailing?.invoke()
    }
}

@Composable
fun Tag(text: String, background: Color, contentColor: Color) {
    Box(
        modifier = Modifier
            .clip(ChipShape)
            .background(background)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(text, style = MaterialTheme.typography.labelSmall, color = contentColor)
    }
}

@Composable
fun DotIndicator(color: Color, size: androidx.compose.ui.unit.Dp = 8.dp) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun ProgressRing(
    percent: Int,
    ringColor: Color,
    trackColor: Color = DividerTone,
    size: androidx.compose.ui.unit.Dp = 56.dp,
    strokeWidth: androidx.compose.ui.unit.Dp = 6.dp,
    centerLabel: String = "$percent%"
) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(size)) {
        androidx.compose.foundation.Canvas(modifier = Modifier.matchParentSize()) {
            val stroke = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            val diameter = size.toPx() - strokeWidth.toPx()
            val topLeft = androidx.compose.ui.geometry.Offset(
                (this.size.width - diameter) / 2f,
                (this.size.height - diameter) / 2f
            )
            drawArc(
                color = trackColor, startAngle = -90f, sweepAngle = 360f,
                useCenter = false, topLeft = topLeft, size = Size(diameter, diameter), style = stroke
            )
            drawArc(
                color = ringColor, startAngle = -90f, sweepAngle = 360f * (percent / 100f),
                useCenter = false, topLeft = topLeft, size = Size(diameter, diameter), style = stroke
            )
        }
        Text(
            centerLabel, style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold, fontSize = 12.sp, color = CharcoalText
        )
    }
}

@Composable
fun WeightedBar(percent: Int, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(ChipShape)
            .background(DividerTone)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(fraction = (percent / 100f).coerceIn(0f, 1f))
                .clip(ChipShape)
                .background(color)
        )
    }
}

@Composable
fun difficultyColor(level: com.example.project2_ui.data_model.Difficulty): Color = when (level) {
    com.example.project2_ui.data_model.Difficulty.EASY -> Difficulty_Easy
    com.example.project2_ui.data_model.Difficulty.MEDIUM -> Difficulty_Medium
    com.example.project2_ui.data_model.Difficulty.HARD -> Difficulty_Hard
}