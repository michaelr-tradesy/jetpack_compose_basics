package com.example.jetpackcomposebasics.main.listofbuttons

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposebasics.DefaultActivity
import com.example.jetpackcomposebasics.examples.button.ButtonExamplesViewModel
import com.example.jetpackcomposebasics.examples.button.DefaultButtonExamplesViewModel
import kotlin.math.abs
import kotlin.random.Random


@ExperimentalUnitApi
class ListOfButtonsExampleActivity : DefaultActivity() {

    private lateinit var viewModel: ButtonExamplesViewModel

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
        viewModel = DefaultButtonExamplesViewModel()
        MyApp {
            MyScreenContent()
        }
    }

    @Composable
    private fun MyScreenContent(names: List<String> = List(1000) { "Hello Android $it" }) {
        var total by remember {
            mutableStateOf(0)
        }
        Column(modifier = modifier.fillMaxHeight()) {
            NamesList(
                names = names,
                modifier = modifier
                    .weight(1f)
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
                callback = { value ->
                    total = value
                }
            )
            if (total > 5) {
                Text(text = "I love to count. New Random Number=[$total]")
            }
        }
    }

    @ExperimentalUnitApi
    @Composable
    private fun NamesList(names: List<String>, modifier: Modifier, callback: (Int) -> Unit) {
        LazyColumn(modifier = modifier) {
            items(items = names) {
                val annotatedString = buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                        withStyle(style = SpanStyle(color = Color(108, 196, 23, 255))) {
                            append("[Start]")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(253, 208, 23 , 255)
                            )
                        ) {
                            append(it)
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(113, 24, 196 , 255)
                            )
                        ) {
                            append("[End]")
                        }
                    }
                }

                BuildButton(
                    modifier = modifier,
                    border = BorderStroke(
                        width = 1.dp,
                        Brush.sweepGradient(
                            0.0f to Color.Red,
                            0.3f to Color.Green,
                            1.0f to Color.Blue,
                            center = Offset(0.0f, 100.0f)
                        )
                    ),
                    onClick = {
                        println("Clicked=[${annotatedString.text}]")
                        callback.invoke(abs(Random.nextInt()))
                    },
                    content = {
                        MySpannedString(
                            modifier = modifier, 
                            text = annotatedString
                        )
                    }
                )
                Divider()
            }

        }
    }

    @Composable
    private fun BuildButton(
        modifier: Modifier,
        onClick: () -> Unit,
        enabled: Boolean = true,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        elevation: ButtonElevation? = ButtonDefaults.elevation(),
        shape: Shape = MaterialTheme.shapes.small,
        border: BorderStroke? = null,
        colors: ButtonColors = ButtonDefaults.buttonColors(),
        contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
        content: @Composable RowScope.() -> Unit
    ) {
        Button(
            onClick = onClick,
            modifier = modifier,
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
    private fun MySpannedString(
        text: AnnotatedString,
        modifier: Modifier,
        textStyle: TextStyle = TextStyle.Default,
        maxLines: Int = Int.MAX_VALUE,
        textOverflow: TextOverflow = TextOverflow.Clip,
        shape: Shape = RectangleShape,
        color: Color = MaterialTheme.colors.surface,
        contentColor: Color = contentColorFor(color),
        border: BorderStroke? = null,
        elevation: Dp = 0.dp
    ) {
        var isSelected by remember {
            mutableStateOf(false)
        }
        val currentColor by animateColorAsState(
            targetValue = if(isSelected) MaterialTheme.colors.primary else Color.Transparent,
            animationSpec = tween(durationMillis = 200)
        )

        Surface(
            modifier,
            shape,
            currentColor,
            contentColor,
            border,
            elevation,
        ) {
            Text(
                text = text,
                modifier = modifier
                    .padding(16.dp)
                    .clickable { isSelected = !isSelected },
                style = textStyle,
                maxLines = maxLines,
                overflow = textOverflow
            )
        }
    }
}