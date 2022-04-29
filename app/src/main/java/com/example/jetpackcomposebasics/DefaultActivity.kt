package com.example.jetpackcomposebasics

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.ui.theme.ColorPalette
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme
import com.example.jetpackcomposebasics.ui.theme.getAppThemeColors

abstract class DefaultActivity : ComponentActivity() {

    private var isDarkTheme : Boolean = false
    private lateinit var systemUiController : SystemUiController
    private lateinit var colorPalette : ColorPalette
    protected lateinit var appThemeState : MutableState<AppThemeState>
    protected lateinit var colors : Colors
    private lateinit var modifier: Modifier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComposeView(this).also {
            setContentView(it)
        }.setContent {
            systemUiController = remember { SystemUiController(window) }
            appThemeState = remember { mutableStateOf(AppThemeState()) }
            DefaultPreview()
        }
    }

    protected fun showToastExample() {
        Toast.makeText(this, "Toast Example...", Toast.LENGTH_SHORT).show()
    }

    @Composable
    protected fun MyApp(content: @Composable () -> Unit) {
        isDarkTheme = appThemeState.component1().isDarkTheme
        colorPalette = appThemeState.component1().colorPalette
        colors = getAppThemeColors(isDarkTheme, colorPalette)

        JetpackComposeBasicsTheme(
            systemUiController = systemUiController,
            appThemeState = appThemeState.value
        ) {
            Surface(
                modifier = Modifier.fillMaxSize()
                    .background(
                        color = colors.background,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                content()
            }
        }
    }

    @Preview(
        fontScale = 1.5f,
        name = "Light Mode",
        uiMode = Configuration.UI_MODE_NIGHT_NO,
        showSystemUi = true,
        showBackground = true
    )
    @Preview(
        fontScale = 1.5f,
        name = "Dark Mode",
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showSystemUi = true,
        showBackground = true
    )
    @Composable
    abstract fun DefaultPreview()
}
