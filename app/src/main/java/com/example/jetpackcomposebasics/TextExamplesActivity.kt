package com.example.jetpackcomposebasics

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme


@ExperimentalUnitApi
class TextExamplesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
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
    private fun DefaultPreview() {
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
    private fun MyScreenContent(names: List<String> = List(3) { "Hello Android $it" }) {
        var total by remember {
            mutableStateOf(0)
        }
        Column(modifier = Modifier.fillMaxHeight()) {
            NamesList(
                names = names,
                modifier = Modifier.weight(1f)
            )
            Counter(
                total = total,
                callback = { value ->
                    total = value
                }
            )
            if (total > 5) {
                Text(text = "I love to count")
            }
        }
    }

    @ExperimentalUnitApi
    @Composable
    private fun NamesList(names: List<String>, modifier: Modifier) {
        LazyColumn(modifier = modifier) {
            items(items = names) {
                val annotatedString = buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                        withStyle(style = SpanStyle(color = Color(108, 196, 23, 255))) {
                            append("[Start Annotated Text] \n")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(253, 208, 23 , 255)
                            )
                        ) {
                            append("$it\n")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(113, 24, 196 , 255)
                            )
                        ) {
                            append("[ENd Annotated Text]")
                        }
                    }
                }
                MySpannedString(text = annotatedString)
                Divider()
                Greeting(text = "Simple Text: $it")
                Greeting(
                    text = "Text with bigger font size",
                    textStyle = TextStyle(
                        fontSize = 24.sp
                    )
                )
                Greeting(
                    text = "Text with Red color",
                    textStyle = TextStyle(
                        color = Color.Red
                    )
                )
                Greeting(
                    text = "Text in bold",
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
                Greeting(
                    text = "Text in a cursive font family",
                    textStyle = TextStyle(
                        fontFamily = FontFamily.Cursive
                    )
                )
                Greeting(
                    text = "Text with Italic font style, restricted as maximum lines as one. " +
                            "Also Providing the padding of 24 density pixels to start, top and end od it. " +
                            "And the end, text will get overflowed and end with an ellipsis of 3 dots.",
                    textStyle = TextStyle(
                        fontStyle = FontStyle.Italic
                    ),
                    maxLines = 2,
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
                    textOverflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.padding(top = 8.dp))
                Divider(color = Color.LightGray)

                Greeting(text = "Text with Padding!", modifier = Modifier.padding(16.dp))

                Divider(color = Color.DarkGray)

                Row(modifier = Modifier.fillMaxWidth()) {
                    Greeting(
                        text = "Text with an underline",
                        textStyle = TextStyle(
                            textDecoration = TextDecoration.Underline,
                            background = Color.Blue
                        )
                    )

                    Greeting(
                        text = "Text with letter spacing",
                        textStyle = TextStyle(
                            textAlign = TextAlign.Center,
                            color = Color.Green,
                            letterSpacing = TextUnit(2f, TextUnitType.Companion.Em)
                        )
                    )
                }
            }

        }
    }

    @Composable
    private fun Greeting(
        text: String,
        modifier: Modifier = Modifier,
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
                text = "Hello $text!",
                modifier = modifier
                    .padding(16.dp)
                    .clickable { isSelected = !isSelected },
                style = textStyle,
                maxLines = maxLines,
                overflow = textOverflow
            )
        }
    }

    @Composable
    private fun MySpannedString(
        text: AnnotatedString,
        modifier: Modifier = Modifier,
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

    @Composable
    private fun Counter(total: Int = 0, callback: (Int) -> Unit) {
        Button(onClick = { callback(total + 1) }) {
            Text(text = "I've been clicked $total times")
        }
    }
}