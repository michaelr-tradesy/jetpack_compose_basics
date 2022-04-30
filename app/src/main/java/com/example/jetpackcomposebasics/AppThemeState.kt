package com.example.jetpackcomposebasics

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import com.example.jetpackcomposebasics.ui.theme.ColorPalette

@Serializable
@Parcelize
data class AppThemeState(
    var isDarkTheme: Boolean = false,
    var colorPalette: ColorPalette = ColorPalette.Bamboo
) : Parcelable
