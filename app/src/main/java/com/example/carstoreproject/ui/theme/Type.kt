package com.example.carstoreproject.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.carstoreproject.R

val PoppinsBold = FontFamily(
    Font(R.font.poppins_bold)
)

val PoppinsRegular = FontFamily(
    Font(R.font.poppins_regular)
)

val PlayFairDisplayBold = FontFamily(
    Font(R.font.playfairdisplay_bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    ),
    headlineSmall = TextStyle(
        fontFamily = PlayFairDisplayBold,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = PoppinsBold,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = PoppinsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = Color.LightGray
    ),
     headlineLarge = TextStyle(
         fontFamily = PlayFairDisplayBold,
         fontWeight = FontWeight.Bold,
         fontSize = 36.sp,
         lineHeight = 24.sp,
         letterSpacing = 0.5.sp,
     )
)