package com.example.jetpackcomposebasics.main

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
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
import androidx.compose.material.CheckboxDefaults.colors


@ExperimentalUnitApi
class CheckboxExamplesActivity : DefaultActivity() {

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
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SimpleCheckbox()
            DisabledCheckbox()
            DisabledColouredCheckbox()
            UncheckedColouredCheckbox()
            CheckedColouredCheckbox()
            DisabledIndeterminateColouredCheckbox()
            LabelledCheckbox()
            GroupedCheckbox(listOf("Checkbox 1", "Checkbox 2", "Checkbox 3"))
        }
    }

    @Composable
    private fun SimpleCheckbox() {
        CreateCheckbox(enabled = true)
    }

    @Composable
    private fun DisabledCheckbox() {
        CreateCheckbox(enabled = false)
    }

    //        checkedColor: Color = MaterialTheme.colors.secondary,
//        uncheckedColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
//        checkmarkColor: Color = MaterialTheme.colors.surface,
//        disabledColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
//        disabledIndeterminateColor: Color = checkedColor.copy(alpha = ContentAlpha.disabled)

    @Composable
    private fun DisabledColouredCheckbox() {
        CreateCheckbox(
            enabled = true,
            colors = colors(disabledColor = Color.LightGray)
        )
    }

    @Composable
    private fun UncheckedColouredCheckbox() {
        CreateCheckbox(
            enabled = true,
            colors = colors(uncheckedColor = Color.Red)
        )
    }

    @Composable
    private fun CheckedColouredCheckbox() {
        CreateCheckbox(
            enabled = true,
            colors = colors(checkedColor = Color.Green)
        )
    }

    @Composable
    private fun DisabledIndeterminateColouredCheckbox() {
        CreateCheckbox(
            checked = true,
            enabled = false,
            colors = colors(
                checkedColor = Color.Green,
                disabledIndeterminateColor = Color.Green.copy(alpha = ContentAlpha.disabled)
            )
        )
    }

    @Composable
    fun LabelledCheckbox() {
        Row(modifier = Modifier.padding(8.dp)) {
            CreateCheckbox(
                enabled = true,
                colors = colors(
                    checkedColor = Color.Green,
                    disabledIndeterminateColor = Color.Green.copy(alpha = ContentAlpha.disabled)
                )
            )
            Text(text = "Labelled Check Box")
        }
    }

    @Composable
    fun GroupedCheckbox(items: List<String>) {

        items.forEach { nextItem ->
            Row(modifier = Modifier.padding(8.dp)) {
                val isChecked = remember { mutableStateOf(false) }

                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = { isChecked.value = it },
                    enabled = true,
                    colors = colors(
                        checkedColor = Color.Magenta,
                        uncheckedColor = Color.DarkGray,
                        checkmarkColor = Color.Cyan
                    )
                )
                Text(text = nextItem)
            }
        }
    }

    @Composable
    private fun CreateCheckbox(
        modifier: Modifier = Modifier,
        checked: Boolean = false,
        enabled: Boolean = true,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        colors: CheckboxColors = colors()
    ) {
        val observer = remember { mutableStateOf(checked) }

        Checkbox(
            modifier = modifier,
            checked = observer.value,
            onCheckedChange = { observer.value = it },
            enabled = enabled,
            interactionSource = interactionSource,
            colors = colors,
        )
    }
}