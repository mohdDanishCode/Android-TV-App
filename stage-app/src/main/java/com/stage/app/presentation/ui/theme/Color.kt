package com.stage.app.presentation.ui.theme

import androidx.compose.ui.graphics.Color

var PrimaryHue = 238f


var PrimaryDark =  getColor("#495AD9",1f)
var PrimaryLight =  getColor("#CCD2FD",1f)
var PrimaryLighter =  getColor("#EEF0FE",1f)
var Primary =  getColor("#5468FA",1f)



var S1 = Color(0xFFFD391D)
var S2 = Color(0xFFFFE5E5)
val E1 = Color(0xFFC21808)
val T1 = Color(0xFF222222)
val T2 = Color(0xFF666666)
val T3 = Color(0xFF999999)

val W1 = Color(0xFFF9720A)

val B1 = Color(0xFFEFEFEF)
val B2 = Color(0xFFF7F9F7)
val B3 = Color(0xFFDEDEDE)
val B4 = Color(0xFFF4F4F4)
val B5 = Color(0xFFEEEFF2)
val B6 = Color(0xFFB1B1B1)
val B7 = Color(0xFFF7F7F7)
val B8 = Color(0xFFFAFAFA)
val B9 = Color(0xFFF4F5F7)

val G1 = Color(0xFF067603)
val G2 = Color(0xFFE6F1E6)


val Black100 = Color(0xFF000000)
val White100 = Color(0xFFFFFFFF)
val Blank = Color(0xFFF9F9F9)


fun getColor(hue: Float, saturation: Float, light: Float, alpha: Float = 100f) = Color.hsl(hue, saturation / 100f, light / 100f).copy(alpha = alpha / 100f)

fun getColorWithDynamicHue(hue: Float?) = getColor(hue ?: PrimaryHue, 50f, 34f)

fun getColor(colorHash: String?, alpha: Float): Color = try {
    Color(
        android.graphics.Color.parseColor(
            colorHash ?: "#000000",
        ),
    ).copy(alpha = alpha) 
} catch (e: Exception) {
    T1.copy(alpha = alpha) }
