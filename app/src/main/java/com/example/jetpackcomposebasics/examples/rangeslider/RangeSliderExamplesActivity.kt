package com.example.jetpackcomposebasics.examples.rangeslider

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.core.util.toRange
import com.example.jetpackcomposebasics.AppThemeState
import com.example.jetpackcomposebasics.DefaultActivity


@ExperimentalMaterialApi
@ExperimentalUnitApi
class RangeSliderExamplesActivity : DefaultActivity() {

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
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SimpleRangeSlider()
            EnabledRangeSlider()
            DisabledRangeSlider()
            CheckedThumbColorRangeSlider()
        }
    }

    @Composable
    fun SimpleRangeSlider() {
        Column(modifier = modifier.padding(8.dp)) {
            Text(text = "Simple RangeSlider")
            CreateRangeSlider(
                modifier = modifier,
                values = 5f.rangeTo(90f),
                onValueChange = { },
            )
        }
    }

    @Composable
    fun EnabledRangeSlider() {
        Column(modifier = modifier.padding(8.dp)) {
            Text(text = "Enabled RangeSlider")
            CreateRangeSlider(
                modifier = modifier,
                values = 50f.rangeTo(75f),
                onValueChange = { },
                enabled = true
            )
        }
    }

    @Composable
    fun DisabledRangeSlider() {
        Column(modifier = modifier.padding(8.dp)) {
            Text(text = "Disabled RangeSlider")
            CreateRangeSlider(
                modifier = modifier,
                values = 20f.rangeTo(60f),
                onValueChange = { },
                enabled = false
            )
        }
    }

    @Composable
    fun CheckedThumbColorRangeSlider() {
        Column(modifier = modifier.padding(8.dp)) {
            Text(text = "Checked Thumb Color RangeSlider")
            CreateRangeSlider(
                modifier = modifier.padding(8.dp),
                values = 10f.rangeTo(25f),
                onValueChange = { },
                enabled = true,
                colors = SliderDefaults.colors(
                    thumbColor = Color.Green,
                    activeTrackColor = Color.Green,
                    activeTickColor = Color.Green.copy(alpha = SliderDefaults.TickAlpha),
                )
            )
        }
    }

    @Composable
    private fun CreateRangeSlider(
        values: ClosedFloatingPointRange<Float>,
        onValueChange: ((ClosedFloatingPointRange<Float>) -> Unit)?,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        valueRange: ClosedFloatingPointRange<Float> = 0f..100f,
        steps: Int = 1,
        onValueChangeFinished: (() -> Unit)? = null,
        colors: SliderColors = SliderDefaults.colors()
    ) {
        var sliderValue by remember { mutableStateOf(values) }
        Card(shape = RoundedCornerShape(4.dp), modifier = modifier.padding(8.dp)) {
            RangeSlider(
                modifier = modifier,
                values = sliderValue,
                onValueChange = { newValue ->
                    sliderValue = newValue
                    onValueChange?.let { it(newValue) }
                },
                enabled = enabled,
                valueRange = valueRange,
                onValueChangeFinished = onValueChangeFinished,
                steps = steps,
                colors = colors
            )
        }
        Text(
            text = "Slider values are %s".format(sliderValue.toString()),
            modifier = modifier.padding(8.dp)
        )
    }
}