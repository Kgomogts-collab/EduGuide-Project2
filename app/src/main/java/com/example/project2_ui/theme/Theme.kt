package com.example.project2_ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val EduGuideColorScheme = lightColorScheme(
    primary = InkNavy,
    onPrimary = Color.White,
    primaryContainer = InkNavyFaint,
    onPrimaryContainer = InkNavy,
    secondary = Coral,
    onSecondary = Color.White,
    secondaryContainer = CoralFaint,
    onSecondaryContainer = CoralDark,
    tertiary = Sage,
    onTertiary = Color.White,
    tertiaryContainer = SageFaint,
    onTertiaryContainer = Sage,
    background = Parchment,
    onBackground = CharcoalText,
    surface = ParchmentCard,
    onSurface = CharcoalText,
    surfaceVariant = ParchmentDim,
    onSurfaceVariant = MutedText,
    outline = DividerTone,
    error = CoralDark,
    onError = Color.White
)

@Composable
fun EduGuideTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EduGuideColorScheme,
        typography = EduGuideTypography,
        shapes = EduGuideShapes,
        content = content
    )
}