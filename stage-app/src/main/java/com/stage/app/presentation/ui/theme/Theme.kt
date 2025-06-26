package com.stage.app.presentation.ui.theme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme
import androidx.tv.material3.lightColorScheme



private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC6),
    tertiary = Color(0xFF3700B3),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC6),
    tertiary = Color(0xFF3700B3),
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)


interface OMFColors {
    val primary: Color
    val primaryLight: Color
    val primaryLighter: Color
    val primaryDark: Color
    val backgroundEmpty: Color
    val background: Color
    val t1: Color
    val t2: Color
    val t3: Color
    val error:Color

}


data class CustomColors(
    override val primary: Color = Primary,
    override val primaryLight: Color = PrimaryLight,
    override val primaryLighter: Color = PrimaryLighter,
    override val primaryDark: Color = PrimaryDark

):OMFColors {
    override val background: Color
        get() = Color.White
    override val backgroundEmpty: Color
        get() = Blank
    override val t1: Color
        get() = T1
    override val t2: Color
        get() = T2
    override val t3: Color
        get() = T3
    override val error: Color
        get() = S1
}

// Function to calculate scale factor based on screen width
fun getScaleFactor(context: Context): Float {
    val displayMetrics = context.resources.displayMetrics
    val screenWidthPx = displayMetrics.widthPixels // Screen width in pixels

    // Convert 360dp to pixels
    val baseWidthDp = 360 // Base width in dp
    val baseWidthPx = dpToPx(context, baseWidthDp) // Convert base width to pixels

    // Calculate scale factor based on screen width
    var scaleFactor = screenWidthPx.toFloat() / baseWidthPx.toFloat()

    // Adjust scale factor for different screen sizes
    scaleFactor = when {
        screenWidthPx < baseWidthPx -> 0.6f // Small screens (e.g., small phones)
        screenWidthPx in baseWidthPx..dpToPx(context, 600) -> 0.8f // Medium screens (e.g., phones)
        else -> 0.9f // Large screens (e.g., tablets)
    }

    return scaleFactor
}

// Function to convert DP to pixels
private fun dpToPx(context: Context, dp: Int): Int {
    val density = context.resources.displayMetrics.density
    return (dp * density).toInt()
}


val LocalOMFColors = staticCompositionLocalOf<OMFColors> {
    error("No Color provided")
}



val LocalTypography = staticCompositionLocalOf<OMFTypography> {
    error("No Typography provided")
}

val LocalSizeSystem = staticCompositionLocalOf<OmfSizeSystem> {
    error("No Size System provided")
}



@Composable
fun OmnifulAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current

    val colors = CustomColors()

    val sizeSystem = DefaultSizeSystem()
    val typography = if (darkTheme) DefaultTypography(sizeSystem) else DefaultTypography(sizeSystem)

    val configuredColorState = ThemeManager.customColors.value ?: colors

    val configuredTypographyState = ThemeManager.customTypography.value ?: typography
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme


    CompositionLocalProvider(
        LocalOMFColors provides configuredColorState,
        LocalTypography provides configuredTypographyState,
        LocalSizeSystem provides sizeSystem
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = androidx.tv.material3.Typography(),
            content = content
        )
    }

}
