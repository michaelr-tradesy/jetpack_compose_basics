package com.example.jetpackcomposebasics.main

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.DefaultActivity
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme


@ExperimentalUnitApi
class SliderExamplesActivity : DefaultActivity() {

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
    override fun DefaultPreview() {
        MyApp {
            MyScreenContent()
        }
    }

    @Composable
    private fun MyApp(content: @Composable () -> Unit) {
        JetpackComposeBasicsTheme {
            // A surface container using the 'background' color from the theme
            Surface(color = MaterialTheme.colors.background) {
                content()
            }
        }
    }

    @Composable
    private fun MyScreenContent() {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SimpleSlider()
            EnabledSlider()
            DisabledSlider()
            CheckedThumbColorSlider()
        }
    }

    @Composable
    fun SimpleSlider() {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Simple Slider")
            CreateSlider(value = 10f)
        }
    }

    @Composable
    fun EnabledSlider() {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Enabled Slider")
            CreateSlider(value = 0f, enabled = true)
        }
    }

    @Composable
    fun DisabledSlider() {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Disabled Slider")
            CreateSlider(value = 0f, enabled = false)
        }
    }

    @Composable
    fun CheckedThumbColorSlider() {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Checked Thumb Color Slider")
            CreateSlider(
                modifier = Modifier.padding(8.dp),
                value = 0f,
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
    private fun CreateSlider(
        modifier: Modifier = Modifier,
        value: Float,
        enabled: Boolean = true,
        valueRange: ClosedFloatingPointRange<Float> = 0f..100f,
        /*@IntRange(from = 0)*/
        steps: Int = 0,
        onValueChangeFinished: (() -> Unit)? = null,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        colors: SliderColors = SliderDefaults.colors()
    ) {
        var sliderValue by remember { mutableStateOf(value) }
        Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
            // A pre-defined composable that's capable of rendering a slider. It
            // honors the Material Design specification.
            Slider(
                value = sliderValue,
                modifier = modifier,
                enabled = enabled,
                valueRange = valueRange,
                steps = steps,
                onValueChangeFinished = onValueChangeFinished,
                interactionSource = interactionSource,
                colors = colors,
                onValueChange = { newValue ->
                sliderValue = newValue
            })
        }
        Text(
            text = "Slider value is %.1f".format(sliderValue),
            modifier = Modifier.padding(8.dp)
        )
    }
}