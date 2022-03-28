package com.example.jetpackcomposebasics

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme

abstract class DefaultActivity : ComponentActivity() {

    protected lateinit var systemUiController : SystemUiController
    protected lateinit var appThemeState : MutableState<AppThemeState>

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
        JetpackComposeBasicsTheme(
            systemUiController = systemUiController,
            appThemeState = appThemeState.value
        ) {
            Surface {
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
