package com.example.jetpackcomposebasics

import com.example.jetpackcomposebasics.ui.theme.ColorPalette

data class AppThemeState(
    var isDarkTheme: Boolean = false,
    var colorPalette: ColorPalette = ColorPalette.Bamboo
)
