package com.example.jetpackcomposebasics.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.jetpackcomposebasics.AppThemeState
import com.example.jetpackcomposebasics.SystemUiController

enum class ColorPalette {
    Bamboo,
    Coral,
    Fuji,
    Jade,
    Orenji,
    Pebble,
    Sakura,
    Sand,
    Sun,
    Wave,
}

// region Dark palettes

private val DarkBambooColorPalette = darkColors(
    primary = Bamboo3,
    primaryVariant = Bamboo4,
    secondary = Bamboo2,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkCoralColorPalette = darkColors(
    primary = Coral3,
    primaryVariant = Coral4,
    secondary = Coral2,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkFujiColorPalette = darkColors(
    primary = Fuji3,
    primaryVariant = Fuji4,
    secondary = Fuji2,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkJadeColorPalette = darkColors(
    primary = Jade3,
    primaryVariant = Jade4,
    secondary = Jade2,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkOrenjiColorPalette = darkColors(
    primary = Orenji3,
    primaryVariant = Orenji4,
    secondary = Orenji2,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkPebbleColorPalette = darkColors(
    primary = Pebble3,
    primaryVariant = Pebble4,
    secondary = Pebble2,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkSakuraColorPalette = darkColors(
    primary = Sakura3,
    primaryVariant = Sakura4,
    secondary = Sakura2,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkSandColorPalette = darkColors(
    primary = Sand3,
    primaryVariant = Sand4,
    secondary = Sand2,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkSunColorPalette = darkColors(
    primary = Sun3,
    primaryVariant = Sun4,
    secondary = Sun2,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

private val DarkWaveColorPalette = darkColors(
    primary = Wave3,
    primaryVariant = Wave4,
    secondary = Wave2,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color.Red,
)

// endregion

// region Light pallets

private val LightBambooColorPalette = lightColors(
    primary = Bamboo3,
    primaryVariant = Bamboo4,
    secondary = Bamboo2,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red,
)

private val LightCoralColorPalette = lightColors(
    primary = Coral3,
    primaryVariant = Coral4,
    secondary = Coral2,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red,
)

private val LightFujiColorPalette = lightColors(
    primary = Fuji3,
    primaryVariant = Fuji4,
    secondary = Fuji2,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red,
)

private val LightJadeColorPalette = lightColors(
    primary = Jade3,
    primaryVariant = Jade4,
    secondary = Jade2,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red,
)

private val LightOrenjiColorPalette = lightColors(
    primary = Orenji3,
    primaryVariant = Orenji4,
    secondary = Orenji2,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red,
)

private val LightPebbleColorPalette = lightColors(
    primary = Pebble3,
    primaryVariant = Pebble4,
    secondary = Pebble2,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red,
)

private val LightSakuraColorPalette = lightColors(
    primary = Sakura3,
    primaryVariant = Sakura4,
    secondary = Sakura2,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red,
)

private val LightSandColorPalette = lightColors(
    primary = Sand3,
    primaryVariant = Sand4,
    secondary = Sand2,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red,
)

private val LightSunColorPalette = lightColors(
    primary = Sun3,
    primaryVariant = Sun4,
    secondary = Sun2,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red,
)

private val LightWaveColorPalette = lightColors(
    primary = Wave3,
    primaryVariant = Wave4,
    secondary = Wave2,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color.Red,
)

// endregion

@Composable
fun JetpackComposeBasicsTheme(
    systemUiController: SystemUiController? = null,
    appThemeState: AppThemeState,
    content: @Composable () -> Unit
) {
    val isDarkTheme = (appThemeState.isSystemOverrideEnabled && isSystemInDarkTheme())
            || (!appThemeState.isSystemOverrideEnabled && appThemeState.isDarkTheme)
    val isSystemOverrideEnabled = appThemeState.isSystemOverrideEnabled
    val colorPalette = appThemeState.colorPalette
    val colors = getAppThemeColors(isDarkTheme, colorPalette)

    systemUiController?.setStatusBarColor(color = colors.primary, darkIcons = isDarkTheme)
    systemUiController?.setNavigationBarColor(color = colors.primary, darkIcons = isDarkTheme)
    systemUiController?.setSystemBarsColor(color = colors.primary, darkIcons = isDarkTheme)

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

fun getAppThemeColors(
    isDarkTheme: Boolean,
    colorPalette: ColorPalette
): Colors {
    val colors = if (isDarkTheme) {
        when (colorPalette) {
            ColorPalette.Bamboo -> DarkBambooColorPalette
            ColorPalette.Coral -> DarkCoralColorPalette
            ColorPalette.Fuji -> DarkFujiColorPalette
            ColorPalette.Jade -> DarkJadeColorPalette
            ColorPalette.Orenji -> DarkOrenjiColorPalette
            ColorPalette.Pebble -> DarkPebbleColorPalette
            ColorPalette.Sakura -> DarkSakuraColorPalette
            ColorPalette.Sand -> DarkSandColorPalette
            ColorPalette.Sun -> DarkSunColorPalette
            ColorPalette.Wave -> DarkWaveColorPalette
        }
    } else {
        when (colorPalette) {
            ColorPalette.Bamboo -> LightBambooColorPalette
            ColorPalette.Coral -> LightCoralColorPalette
            ColorPalette.Fuji -> LightFujiColorPalette
            ColorPalette.Jade -> LightJadeColorPalette
            ColorPalette.Orenji -> LightOrenjiColorPalette
            ColorPalette.Pebble -> LightPebbleColorPalette
            ColorPalette.Sakura -> LightSakuraColorPalette
            ColorPalette.Sand -> LightSandColorPalette
            ColorPalette.Sun -> LightSunColorPalette
            ColorPalette.Wave -> LightWaveColorPalette
        }
    }
    return colors
}