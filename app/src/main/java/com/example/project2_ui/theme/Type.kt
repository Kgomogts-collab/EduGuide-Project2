package com.example.project2_ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DisplayFont = FontFamily.Serif
private val BodyFont = FontFamily.SansSerif

val EduGuideTypography = Typography(
    displaySmall = TextStyle(
        fontFamily = DisplayFont, fontWeight = FontWeight.Bold,
        fontSize = 30.sp, lineHeight = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = DisplayFont, fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp, lineHeight = 28.sp
    ),
    titleLarge = TextStyle(
        fontFamily = DisplayFont, fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp, lineHeight = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = BodyFont, fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp, lineHeight = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = BodyFont, fontWeight = FontWeight.Normal,
        fontSize = 15.sp, lineHeight = 22.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = BodyFont, fontWeight = FontWeight.Normal,
        fontSize = 13.sp, lineHeight = 19.sp
    ),
    labelLarge = TextStyle(
        fontFamily = BodyFont, fontWeight = FontWeight.Medium,
        fontSize = 13.sp, letterSpacing = 0.2.sp
    ),
    labelSmall = TextStyle(
        fontFamily = BodyFont, fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp, letterSpacing = 0.4.sp
    )
)