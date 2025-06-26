package com.stage.app.presentation.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sv.lib.squircleshape.CornerSmoothing
import sv.lib.squircleshape.SquircleShape

interface OmfSizeSystem {
    val scaleFactor : Float
    fun setScaleFactor(scaleFactor: Float) :OmfSizeSystem
    // Text sizes for typography
    val superHero : TextUnit
    val hero : TextUnit
    val h0: TextUnit
    val h1: TextUnit
    val h2: TextUnit
    val h3: TextUnit
    val body:TextUnit
    val subBody:TextUnit
    val assistive:TextUnit

    // Corner radius for rounded elements
    val cornerRadiusNone: Dp
    val cornerRadius1: Dp
    val cornerRadius1v1: Dp
    val cornerRadius2: Dp
    val cornerRadius3: Dp
    val cornerRadius4: Dp
    val cornerRadius5: Dp
    val cornerRadius6: Dp
    val cornerRadius7: Dp
    val cornerRadiusFull: Dp

    val divider0: Dp
    val divider1: Dp

    // Spacing sizes for layout gaps
    val spacing0: Dp
    val spacing0v1: Dp
    val spacing1: Dp
    val spacing2: Dp
    val spacing3: Dp
    val spacing4: Dp
    val spacing5: Dp
    val spacing6: Dp
    val spacing7: Dp
    val spacing8: Dp
    val spacing9: Dp
    val spacing10: Dp

    // Spacing sizes for layout gaps
    val strokeHalf: Dp
    val stroke0: Dp
    val stroke1: Dp
    val stroke2: Dp
    val stroke3: Dp

    val textFieldHeight: Dp
    val searchFieldHeight: Dp
    val searchFieldHeightv1: Dp
    val otpWidth:Dp
    val buttonHeight: Dp
    val buttonHeightv1: Dp
    val iconSizeExtraSmall: Dp
    val iconSizeVerySmall: Dp
    val iconSizeBitSmall: Dp
    val iconSizeSmall: Dp
    val iconSizeMedium: Dp
    val iconSizeBitLarge: Dp
    val iconSizeLarge: Dp
    val iconSizeXLarge: Dp
    val iconSizeVeryLarge: Dp
    val bottomBarHeight: Dp
    val dialogWidth: Dp
    val imageSizeSmall: Dp
    val imageSizeMedium: Dp
    val placeHolderIcon:Dp
    val pinDigitSize:Dp

    fun shape(cornerRadius:Dp): CornerBasedShape



}

class DefaultSizeSystem : OmfSizeSystem {
    override var scaleFactor: Float = 1f

    override fun setScaleFactor(scaleFactor: Float):DefaultSizeSystem {
        this.scaleFactor = scaleFactor
        return this
    }


    override val superHero: TextUnit
        get() = 32.sp*scaleFactor
    override val hero: TextUnit
        get() = 28.sp*scaleFactor
    override val h0: TextUnit
        get() = 24.sp*scaleFactor
    override val h1: TextUnit
        get() = 20.sp*scaleFactor
    override val h2: TextUnit
        get() = 18.sp*scaleFactor
    override val h3: TextUnit
        get() = 16.sp*scaleFactor
    override val body: TextUnit
        get() = 14.sp*scaleFactor
    override val subBody: TextUnit
        get() = 12.sp*scaleFactor
    override val assistive: TextUnit
        get() = 11.sp*scaleFactor


    override val cornerRadiusNone: Dp
        get() = 0.dp*scaleFactor
    override val cornerRadius1: Dp
        get() = 8.dp*scaleFactor
    override val cornerRadius1v1: Dp
        get() = 12.dp*scaleFactor
    override val cornerRadius2: Dp
        get() = 16.dp*scaleFactor
    override val cornerRadius3: Dp
        get() = 24.dp*scaleFactor
    override val cornerRadius4: Dp
        get() = 32.dp*scaleFactor
    override val cornerRadius5: Dp
        get() = 40.dp*scaleFactor
    override val cornerRadius6: Dp
        get() = 48.dp*scaleFactor
    override val cornerRadius7: Dp
        get() = 64.dp*scaleFactor
    override val cornerRadiusFull: Dp
        get() = 999.dp*scaleFactor

    override val divider0: Dp
        get() = 0.5.dp*scaleFactor
    override val divider1: Dp
        get() = 1.dp*scaleFactor

    override val spacing0: Dp
        get() = 0.dp*scaleFactor
    override val spacing0v1: Dp
        get() = 2.dp*scaleFactor
    override val spacing1: Dp
        get() = 4.dp*scaleFactor
    override val spacing2: Dp
        get() = 8.dp*scaleFactor
    override val spacing3: Dp
        get() = 12.dp*scaleFactor
    override val spacing4: Dp
        get() = 16.dp*scaleFactor
    override val spacing5: Dp
        get() = 20.dp*scaleFactor
    override val spacing6: Dp
        get() = 24.dp*scaleFactor
    override val spacing7: Dp
        get() = 32.dp*scaleFactor
    override val spacing8: Dp
        get() = 40.dp*scaleFactor
    override val spacing9: Dp
        get() = 48.dp*scaleFactor
    override val spacing10: Dp
        get() = 56.dp*scaleFactor

    override val strokeHalf: Dp
        get() = 0.5.dp*scaleFactor
    override val stroke0: Dp
        get() = 1.dp*scaleFactor
    override val stroke1: Dp
        get() = 2.dp*scaleFactor
    override val stroke2: Dp
        get() = 4.dp*scaleFactor
    override val stroke3: Dp
        get() = 6.dp*scaleFactor

    override val textFieldHeight: Dp
        get() = 58.dp*scaleFactor
    override val searchFieldHeight: Dp
        get() = 72.dp*scaleFactor
    override val searchFieldHeightv1: Dp
        get() = 52.dp*scaleFactor
    override val otpWidth: Dp
        get() = 64.dp*scaleFactor
    override val buttonHeight: Dp
        get() = 62.dp*scaleFactor
    override val buttonHeightv1: Dp
        get() = 52.dp*scaleFactor
    override val iconSizeExtraSmall: Dp
        get() = 8.dp*scaleFactor
    override val iconSizeVerySmall: Dp
        get() = 12.dp*scaleFactor
    override val iconSizeBitSmall: Dp
        get() = 16.dp*scaleFactor
    override val iconSizeSmall: Dp
        get() = 20.dp*scaleFactor
    override val iconSizeMedium: Dp
        get() = 24.dp*scaleFactor
    override val iconSizeBitLarge: Dp
        get() = 28.dp*scaleFactor
    override val iconSizeLarge: Dp
        get() = 32.dp*scaleFactor
    override val iconSizeXLarge: Dp
        get() = 36.dp*scaleFactor
    override val iconSizeVeryLarge: Dp
        get() = 80.dp*scaleFactor
    override val bottomBarHeight: Dp
        get() = 92.dp*scaleFactor
    override val dialogWidth: Dp
        get() = 512.dp*scaleFactor
    override val imageSizeSmall: Dp
        get() = 48.dp*scaleFactor
    override val imageSizeMedium: Dp
        get() = 64.dp*scaleFactor
    override val placeHolderIcon: Dp
        get() = 160.dp*scaleFactor
    override val pinDigitSize: Dp
        get() = 72.dp*scaleFactor


    override fun shape(cornerRadius: Dp): CornerBasedShape {
//        return RoundedCornerShape(cornerRadius)
        return SquircleShape(radius = cornerRadius*1.1f, cornerSmoothing = CornerSmoothing.Medium)
    }
}


