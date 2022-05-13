package com.example.jetpackcomposebasics.examples.progressindicator

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.AppThemeState
import com.example.jetpackcomposebasics.DefaultActivity


@ExperimentalUnitApi
class ProgressIndicatorExamplesActivity : DefaultActivity() {

    private lateinit var observer: MutableState<String?>

    @Preview(
        fontScale = 1.5f,
        name = "Light Mode",
        uiMode = Configuration.UI_MODE_NIGHT_NO,
        showSystemUi = true,
        showBackground = true,
    )
    @Composable
    private fun PreviewLightMode() {
        modifier = Modifier
        appThemeState = remember { mutableStateOf(AppThemeState()) }

        DefaultPreview()
    }

    @Preview(
        fontScale = 1.5f,
        name = "Dark Mode",
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showSystemUi = true,
        showBackground = true
    )
    @Composable
    private fun PreviewDarkMode() {
        modifier = Modifier
        appThemeState = remember { mutableStateOf(AppThemeState(isDarkTheme = true)) }

        DefaultPreview()
    }

    @Composable
    override fun DefaultPreview() {
        MyApp {
            MyScreenContent()
        }
    }

    @Composable
    private fun MyScreenContent() {
        this.observer = remember { mutableStateOf(null) }

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SimpleCircularProgressIndicator()
            FeaturedCircularProgressIndicator()
            SimpleLinearProgressIndicator()
            FeaturedLinearProgressIndicator()
        }
    }

    @Composable
    private fun SimpleLinearProgressIndicator() {
        CreateLinearProgressIndicator(modifier = modifier)
    }

    @Composable
    private fun FeaturedLinearProgressIndicator() {
        CreateLinearProgressIndicator(
            modifier = modifier.padding(8.dp),
            color = Color.Green,
            backgroundColor = Color.Red
        )
    }

    @Composable
    private fun SimpleCircularProgressIndicator() {
        CreateCircularProgressIndicator(modifier = modifier)
    }

    @Composable
    private fun FeaturedCircularProgressIndicator() {
        CreateCircularProgressIndicator(
            progress = 0.8f,
            modifier = modifier.padding(8.dp),
            color = Color.Green,
            strokeWidth = 2.dp
        )
    }

    @Composable
    private fun CreateLinearProgressIndicator(
        modifier: Modifier,
        color: Color = MaterialTheme.colors.primary,
        backgroundColor: Color = color.copy(alpha = ProgressIndicatorDefaults.IndicatorBackgroundOpacity)
    ) {
        LinearProgressIndicator(
            modifier = modifier,
            color = color,
            backgroundColor = backgroundColor
        )
    }

    @Composable
    private fun CreateCircularProgressIndicator(
        modifier: Modifier,
        progress: Float = 0.0f,
        color: Color = MaterialTheme.colors.primary,
        strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth
    ) {
        val infiniteTransition = rememberInfiniteTransition()
        val progressAnimationValue by infiniteTransition.animateFloat(
            initialValue = progress,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(animation = tween(900))
        )

        CircularProgressIndicator(
            modifier = modifier,
            progress = progressAnimationValue,
            color = color,
            strokeWidth = strokeWidth
        )
    }
}