package com.example.jetpackcomposebasics.examples.radiobutton

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.AppThemeState
import com.example.jetpackcomposebasics.DefaultActivity


@ExperimentalUnitApi
class RadioButtonExamplesActivity : DefaultActivity() {

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
            SimpleRadioButton()
            DisabledRadioButton()
            EnabledRadioButton()
            DisabledColouredRadioButton()
            EnabledColouredRadioButton()
            UnselectedColouredRadioButton()
            SelectedColouredRadioButton()
            GroupedRadioButton(listOf("Item 1", "Item 2", "Item 3"))
            LabelledRadioButton("Labelled Radio Button # 1")
            LabelledRadioButton("Labelled Radio Button # 2")
        }
    }

    @Composable
    private fun SimpleRadioButton() {
        CreateRadioButtonComponent(modifier = modifier, text = "Simple")
    }

    @Composable
    private fun DisabledRadioButton() {
        CreateRadioButtonComponent(modifier = modifier, text = "Disabled", enabled = false)
    }

    @Composable
    private fun EnabledRadioButton() {
        CreateRadioButtonComponent(modifier = modifier, text = "Enabled", enabled = true)
    }

    @Composable
    private fun DisabledColouredRadioButton() {
        CreateRadioButtonComponent(
            modifier = modifier,
            text = "Disabled Coloured",
            enabled = false,
            colors = RadioButtonDefaults.colors(disabledColor = Color.Gray)
        )
    }

    @Composable
    private fun EnabledColouredRadioButton() {
        CreateRadioButtonComponent(
            modifier = modifier,
            text = "Enabled Coloured",
            enabled = true,
            colors = RadioButtonDefaults.colors(selectedColor = Color.Blue)
        )
    }

    @Composable
    private fun UnselectedColouredRadioButton() {
        CreateRadioButtonComponent(
            modifier = modifier,
            text = "Unselected Coloured",
            enabled = true,
            colors = RadioButtonDefaults.colors(unselectedColor = Color.DarkGray)
        )
    }

    @Composable
    private fun SelectedColouredRadioButton() {
        CreateRadioButtonComponent(
            modifier = modifier,
            text = "Selected Coloured",
            enabled = true,
            colors = RadioButtonDefaults.colors(selectedColor = Color.Green)
        )
    }

    @Composable
    private fun GroupedRadioButton(items: List<String>) {
        Column {
            items.forEach { nextItem ->
                Row {
                    CreateRadioButtonComponent(
                        modifier = modifier,
                        text = nextItem,
                        enabled = true,
                        colors = RadioButtonDefaults.colors(selectedColor = Color.Magenta)
                    )
                }
                Text(text = nextItem, modifier = modifier.padding(start = 8.dp))
            }
        }
    }

    @Composable
    private fun LabelledRadioButton(text: String) {
        Row {
            CreateRadioButtonComponent(
                modifier = modifier,
                text = text,
                enabled = true,
                colors = RadioButtonDefaults.colors(selectedColor = Color.Magenta)
            )
            Text(text = text, modifier = modifier.padding(start = 8.dp))
        }
    }

    @Composable
    private fun CreateRadioButtonComponent(
        modifier: Modifier,
        text: String? = null,
        enabled: Boolean = true,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        colors: RadioButtonColors = RadioButtonDefaults.colors()
    ) {
        RadioButton(
            modifier = modifier,
            colors = colors,
            enabled = enabled,
            interactionSource = interactionSource,
            selected = observer.value == text,
            onClick = { observer.value = text })
    }
}