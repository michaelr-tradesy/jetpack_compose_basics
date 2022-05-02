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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.appThemeState.AppThemeDataStore
import com.example.jetpackcomposebasics.ui.theme.ColorPalette
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme
import com.example.jetpackcomposebasics.ui.theme.getAppThemeColors
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class DefaultActivity : ComponentActivity() {

    private var isDarkTheme : Boolean = false
    private var systemUiController : SystemUiController? = null
    private lateinit var colorPalette : ColorPalette
    private lateinit var appThemeDataStore : AppThemeDataStore
    private var job: Job? = null
    protected lateinit var modifier: Modifier
    protected lateinit var appThemeState : MutableState<AppThemeState>
    protected lateinit var colors : Colors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComposeView(this).also {
            setContentView(it)
        }.setContent {
            modifier = Modifier
            appThemeDataStore = AppThemeDataStore.instance
            systemUiController = remember { SystemUiController(window) }
            appThemeState = remember { mutableStateOf(AppThemeState()) }
            DefaultPreview()
        }
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    protected fun showToastExample() {
        Toast.makeText(this, "Toast Example...", Toast.LENGTH_SHORT).show()
    }

    @Composable
    protected fun MyApp(content: @Composable () -> Unit) {
        isDarkTheme = appThemeState.component1().isDarkTheme
        colorPalette = appThemeState.component1().colorPalette
        colors = getAppThemeColors(isDarkTheme, colorPalette)

        LaunchedEffect(Unit) {
            job = launch { appThemeDataStore.listen(this@DefaultActivity) { appThemeState.value = it } }
        }

        JetpackComposeBasicsTheme(
            systemUiController = systemUiController,
            appThemeState = appThemeState.value
        ) {
            Surface(
                modifier = modifier.fillMaxSize()
                    .background(
                        color = colors.background,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                content()
            }
        }
    }

    protected fun newAppThemeState(state: AppThemeState) {
        appThemeDataStore.set(this, state)
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
