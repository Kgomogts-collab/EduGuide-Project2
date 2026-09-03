package com.example.project2_ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val CardShapeSignature = RoundedCornerShape(
    topStart = 22.dp, topEnd = 6.dp,
    bottomEnd = 22.dp, bottomStart = 6.dp
)

val ChipShape = RoundedCornerShape(50)
val SheetShape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)

val EduGuideShapes = Shapes(
    extraSmall = RoundedCornerShape(6.dp),
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)