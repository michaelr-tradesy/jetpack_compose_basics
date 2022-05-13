package com.example.jetpackcomposebasics.examples.button

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.ButtonDefaults.outlinedButtonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.AppThemeState
import com.example.jetpackcomposebasics.DefaultActivity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@ExperimentalComposeUiApi
@ExperimentalUnitApi
class ButtonExamplesActivity : DefaultActivity() {

    private lateinit var viewModel: ButtonExamplesViewModel

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
        viewModel = DefaultButtonExamplesViewModel()
        MyApp {
            MyScreenContent()
        }
    }

    @Composable
    private fun MyScreenContent() {
        val value = remember { mutableStateOf(0) }

        Column(modifier = modifier.fillMaxSize()) {
//            SimpleButtonComponent()
//            BorderedButtonComponent()
            RoundCorneredButtonComponent()
            CutCorneredButtonComponent()
            DisabledButtonComponent()
            OutlinedButtonComponent()
            TextButtonComponent()
            IconToggleButtonComponent()
            IconButtonComponent()
            RepeatingButton(
                modifier = modifier,
                onClick = { value.value += 1 }) {
                Text(
                    modifier = modifier.padding(16.dp),
                    text = "Repeating Button Click Count=[${value.value}]"
                )
            }
        }
    }

    @Composable
    private fun RepeatingButton(
        modifier: Modifier,
        onClick: () -> Unit,
        enabled: Boolean = true,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        elevation: ButtonElevation? = ButtonDefaults.elevation(),
        shape: Shape = MaterialTheme.shapes.small,
        border: BorderStroke? = null,
        colors: ButtonColors = ButtonDefaults.buttonColors(),
        contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
        maxDelayMillis: Long = 1000,
        minDelayMillis: Long = 5,
        delayDecayFactor: Float = .15f,
        content: @Composable RowScope.() -> Unit
    ) {

        val currentClickListener by rememberUpdatedState(onClick)

        Button(
            modifier = modifier.pointerInput(interactionSource, enabled) {
                forEachGesture {
                    coroutineScope {
                        awaitPointerEventScope {
                            val down = awaitFirstDown(requireUnconsumed = false)

                            val heldButtonJob = launch {
                                var currentDelayMillis = maxDelayMillis
                                while (enabled && down.pressed) {
                                    currentClickListener()
                                    delay(currentDelayMillis)
                                    val nextDelayMillis =
                                        currentDelayMillis - (currentDelayMillis * delayDecayFactor)
                                    currentDelayMillis =
                                        nextDelayMillis.toLong().coerceAtLeast(minDelayMillis)
                                }
                            }

                            waitForUpOrCancellation()
                            heldButtonJob.cancel()
                        }
                    }
                }
            },
            onClick = {},
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = elevation,
            shape = shape,
            border = border,
            colors = colors,
            contentPadding = contentPadding,
            content = content
        )
    }

    @Composable
    private fun RoundCorneredButtonComponent() {
        ButtonComponentWithElements(
            shape = RoundedCornerShape(8.dp),
            text = "Cut Corner Button Example"
        )
    }

    @Composable
    private fun CutCorneredButtonComponent() {
        ButtonComponentWithElements(
            shape = CutCornerShape(8.dp),
            text = "Cut Corner Button Example"
        )
    }

    @Composable
    private fun ButtonComponentWithElements(
        shape: Shape,
        @Suppress("SameParameterValue") text: String
    ) {
        CreateTextButtonComponent(
            modifier = modifier.padding(8.dp),
            border = BorderStroke(1.dp, SolidColor(Color.Blue)),
            onClick = { showToastExample() },
            shape = shape,
            elevation = elevation(
                defaultElevation = 2.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp
            ),
            colors = outlinedButtonColors(),
            contentPadding = PaddingValues(
                start = 8.dp,
                top = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
        ) {
            Text(
                modifier = modifier
                    .padding(16.dp)
                    .clickable { showToastExample() },
                text = text
            )
        }
    }

    @Composable
    private fun DisabledButtonComponent() {
        val colors = if (!isSystemInDarkTheme()) {
            outlinedButtonColors(backgroundColor = Color.DarkGray)
        } else {
            outlinedButtonColors()
        }
        val border = if (!isSystemInDarkTheme()) {
            BorderStroke(1.dp, SolidColor(Color.LightGray))
        } else {
            null
        }
        CreateTextButtonComponent(
            modifier = modifier.padding(8.dp),
            enabled = false,
            border = border,
            onClick = { showToastExample() },
            elevation = elevation(
                defaultElevation = 2.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp
            ),
            colors = colors,
            contentPadding = PaddingValues(
                start = 8.dp,
                top = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
        ) {
            Text(
                modifier = modifier
                    .padding(16.dp)
                    .clickable { showToastExample() },
                text = "Disabled Button Example"
            )
        }
    }

    @Composable
    private fun OutlinedButtonComponent() {
        CreateTextButtonComponent(
            modifier = modifier.padding(8.dp),
            border = BorderStroke(1.dp, SolidColor(Color.Blue)),
            onClick = { showToastExample() },
            elevation = elevation(
                defaultElevation = 2.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp
            ),
            colors = outlinedButtonColors(),
            contentPadding = PaddingValues(
                start = 8.dp,
                top = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
        ) {
            Text(
                modifier = modifier
                    .padding(16.dp)
                    .clickable { showToastExample() },
                text = "Outlined Button Example"
            )
        }
    }

    @Composable
    private fun TextButtonComponent() {
        CreateTextButtonComponent(
            modifier = modifier.padding(8.dp),
            onClick = { showToastExample() },
            elevation = elevation(
                defaultElevation = 2.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp
            ),
            contentPadding = PaddingValues(
                start = 8.dp,
                top = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
        ) {
            Text(
                modifier = modifier
                    .padding(16.dp)
                    .clickable { showToastExample() },
                text = "Text Button Example"
            )
        }
    }

    @Composable
    private fun CreateTextButtonComponent(
        modifier: Modifier,
        border: BorderStroke? = null,
        onClick: () -> Unit,
        enabled: Boolean = true,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        elevation: ButtonElevation? = null,
        shape: Shape = MaterialTheme.shapes.small,
        colors: ButtonColors = ButtonDefaults.textButtonColors(),
        contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
        content: @Composable RowScope.() -> Unit
    ) {
        TextButton(
            modifier = modifier,
            border = border,
            onClick = onClick,
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = elevation,
            shape = shape,
            colors = colors,
            contentPadding = contentPadding,
            content = content
        )
    }

    @Composable
    private fun IconButtonComponent() {
        IconButton(
            onClick = {

            },
            modifier = modifier.padding(0.dp),
        ) {
            Icon(
                Icons.Rounded.Home,
                contentDescription = "Icon Button",
                modifier = modifier
                    // Set image size to 40 dp
                    .size(40.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
        }
    }

    @Composable
    private fun IconToggleButtonComponent() {
        IconToggleButton(
            modifier = modifier.padding(0.dp),
            checked = false,
            onCheckedChange = {

            },
            enabled = false
        ) {
            Image(
                painter = painterResource(android.R.drawable.ic_delete),
                contentDescription = "Icon Toggle Button",
                modifier = modifier
                    // Set image size to 40 dp
                    .size(40.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
        }
    }
}
