package com.stage.app.presentation.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.stage.app.R


private val OMFFontFamily = FontFamily(
    listOf(
        Font(R.font.poppins_extralight, FontWeight.ExtraLight),
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_thin, FontWeight.Thin),
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
    ),
)


interface OMFTypography {
    val sizeSystem: OmfSizeSystem
    fun superHero(color: Color = T1): TextStyle
    fun hero(color: Color = T1): TextStyle
    fun h0SemiBold(color: Color = T1): TextStyle
    fun h1SemiBold(color: Color = T1): TextStyle
    fun h1Medium(color: Color = T1): TextStyle
    fun h1Regular(color: Color = T1): TextStyle
    fun h2SemiBold(color: Color = T1): TextStyle
    fun h2Medium(color: Color = T1): TextStyle
    fun h2Regular(color: Color = T1): TextStyle
    fun h3SemiBold(color: Color = T1): TextStyle
    fun h3Medium(color: Color = T1): TextStyle
    fun h3Regular(color: Color = T1): TextStyle
    fun bodySemiBold(color: Color = T1): TextStyle
    fun bodyMedium(color: Color = T1): TextStyle
    fun bodyRegular(color: Color = T1): TextStyle
    fun subSemiBold(color: Color = T1): TextStyle
    fun subMedium(color: Color = T1): TextStyle
    fun subRegular(color: Color = T1): TextStyle
    fun assistiveMedium(color: Color = T1): TextStyle
    fun assistiveRegular(color: Color = T1): TextStyle
}

class DefaultTypography(override val sizeSystem: OmfSizeSystem) : OMFTypography {
    override fun superHero(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.Normal,
            color = color,
            fontSize = sizeSystem.superHero
        )
    }

    override fun hero(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontSize = sizeSystem.hero
        )
    }

    override fun h0SemiBold(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontSize = sizeSystem.h0
        )
    }

    override fun h1SemiBold(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontSize = sizeSystem.h1
        )
    }

    override fun h1Medium(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.Medium,
            color = color,
            fontSize = sizeSystem.h1
        )
    }

    override fun h1Regular(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.Normal,
            color = color,
            fontSize = sizeSystem.h1
        )
    }

    override fun h2SemiBold(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontSize = sizeSystem.h2
        )
    }

    override fun h2Medium(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.Medium,
            color = color,
            fontSize = sizeSystem.h1
        )
    }

    override fun h2Regular(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.Normal,
            color = color,
            fontSize = sizeSystem.h1
        )
    }

    override fun h3SemiBold(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontSize = sizeSystem.h3
        )
    }

    override fun h3Medium(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.Medium,
            color = color,
            fontSize = sizeSystem.h3
        )
    }

    override fun h3Regular(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.Normal,
            color = color,
            fontSize = sizeSystem.h3
        )
    }

    override fun bodySemiBold(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontSize = sizeSystem.body
        )
    }

    override fun bodyMedium(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.Medium,
            color = color,
            fontSize = sizeSystem.body
        )
    }

    override fun bodyRegular(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.Normal,
            color = color,
            fontSize = sizeSystem.body
        )
    }

    override fun subSemiBold(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontSize = sizeSystem.subBody
        )
    }

    override fun subMedium(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.Medium,
            color = color,
            fontSize = sizeSystem.subBody
        )
    }

    override fun subRegular(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.Normal,
            color = color,
            fontSize = sizeSystem.subBody
        )
    }

    override fun assistiveMedium(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.Medium,
            color = color,
            fontSize = sizeSystem.assistive
        )
    }

    override fun assistiveRegular(color: Color): TextStyle {
        return TextStyle(
            fontFamily = OMFFontFamily,
            fontWeight = FontWeight.Normal,
            color = color,
            fontSize = sizeSystem.assistive
        )
    }


}


