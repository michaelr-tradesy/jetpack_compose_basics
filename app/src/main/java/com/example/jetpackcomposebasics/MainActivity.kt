package com.example.jetpackcomposebasics

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Math.abs
import kotlin.math.roundToInt


@ExperimentalUnitApi
class MainActivity : ComponentActivity() {

    private enum class Type {
        SnackBar,
        None
    }

    private lateinit var scaffoldState: ScaffoldState
    private lateinit var scope: CoroutineScope
    private lateinit var clickCount: MutableState<Int>
//    private lateinit var animatedProgress : MutableState<Animatable>

    private val showDialog = mutableStateOf(Type.None)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComposeView(this).also {
            setContentView(it)
        }.setContent {
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
        // Consider negative values to mean 'cut corner' and positive values to mean 'round corner'
        val sharpEdgePercent = -50f
        val roundEdgePercent = 45f
// Start with sharp edges
        val animatedProgress = remember { Animatable(sharpEdgePercent) }
// Create a coroutineScope for the animation
        val coroutineScope = rememberCoroutineScope()
// animation value to animate shape
        val progress = animatedProgress.value.roundToInt()

// When progress is 0, there is no modification to the edges so we are just drawing a rectangle.
// This allows for a smooth transition between cut corners and round corners.
        val fabShape = if (progress < 0) {
            CutCornerShape(abs(progress))
        } else if (progress == roundEdgePercent.toInt()) {
            CircleShape
        } else {
            RoundedCornerShape(progress)
        }
// lambda to call to trigger shape animation
        val changeShape: () -> Unit = {
            val target = animatedProgress.targetValue
            val nextTarget = if (target == roundEdgePercent) sharpEdgePercent else roundEdgePercent
            coroutineScope.launch {
                animatedProgress.animateTo(
                    targetValue = nextTarget,
                    animationSpec = TweenSpec(durationMillis = 600)
                )
            }
        }
        scaffoldState = rememberScaffoldState()
        scope = rememberCoroutineScope()
        clickCount = remember { mutableStateOf(0) }

        MyApp {
//        OnShowSnackBarExamples()
            Scaffold(
                scaffoldState = scaffoldState,
                snackbarHost = {
                    // reuse default SnackbarHost to have default animation and timing handling
                    SnackbarHost(it) { data ->
                        // custom snackbar with the custom border
                        Snackbar(
                            modifier = Modifier.border(2.dp, MaterialTheme.colors.secondary),
                            snackbarData = data
                        )
                    }
                },
                topBar = { TopAppBar(title = { Text("Scaffold with bottom cutout") }) },
                bottomBar = {
                    BottomAppBar(cutoutShape = fabShape) {
                        IconButton(
                            onClick = {
                                coroutineScope.launch { scaffoldState.drawerState.open() }
                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                        }
                    }
                },
                drawerContent = {
                    Text("Drawer title", modifier = Modifier.padding(16.dp))
                    Divider()
                    // Drawer items
                },
                floatingActionButton = {
                    BuildFloatingActionButton()
                },
                floatingActionButtonPosition = FabPosition.Center,
                drawerGesturesEnabled = true,
                isFloatingActionButtonDocked = true,
            ) {
                // Screen content
                // reference:
                // https://github.com/Foso/Jetpack-Compose-Playground/tree/master/mysamples/src/main/java/de/jensklingenberg/jetpackcomposeplayground/mysamples/github/material
                val list: List<Pair<String, Type>> =
                    listOf(
                        Pair("Alert Dialog Examples", Type.None),
                        Pair("Top App Bar Examples", Type.None),
                        Pair("Badge Box Examples", Type.None),
                        Pair("Button Examples", Type.None),
                        Pair("Card Examples", Type.None),
                        Pair("Checkbox Examples", Type.None),
                        Pair("Circular Progress Examples", Type.None),
                        Pair("Custom View Examples", Type.None),
                        Pair("Drop Down List Examples", Type.None),
                        Pair("Edit Text Field Examples", Type.None),
                        Pair("Floating Action Button Examples", Type.None),
                        Pair("Linear Progress Examples", Type.None),
                        Pair("Modal Bottom Sheet Layout Examples", Type.None),
                        Pair("Modal Drawer Examples", Type.None),
                        Pair("Radio Button Examples", Type.None),
                        Pair("Scaffold Examples", Type.None),
                        Pair("Slider Examples", Type.None),
                        Pair("Range Slider Examples", Type.None),
                        Pair("Snack Bar Examples", Type.SnackBar),
                        Pair("Surface Examples", Type.None),
                        Pair("Text Field Examples", Type.None),
                        Pair("Switch Examples", Type.None),
                    )

                MyScreenContent(list)
            }
        }
    }

    @Composable
    fun BuildFloatingActionButton() {
        ExtendedFloatingActionButton(
            onClick = {
                scope.launch {
                    val result = scaffoldState.snackbarHostState
                        .showSnackbar(
                            message = "Snackbar # ${clickCount.value}",
                            actionLabel = "Action",
                            // Defaults to SnackbarDuration.Short
                            duration = SnackbarDuration.Indefinite
                        ).also { clickCount.value = clickCount.value + 1 }

                    when (result) {
                        SnackbarResult.ActionPerformed -> {
                            /* Handle snackbar action performed */
                        }
                        SnackbarResult.Dismissed -> {
                            /* Handle snackbar dismissed */
                        }
                    }
                }
            },
            icon = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite"
                )
            },
            text = { Text("Like") }
        )
    }

    @Composable
    private fun OnShowSnackBarExamples(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current) {
        val currentOnStart by rememberUpdatedState(true)

        val snackBarHostState = remember { mutableStateOf(SnackbarHostState()) }


//        lifecycleScope.launch {
//            val time = System.currentTimeMillis()
//            snackbarHostState.value.showSnackbar(
//                message = "Hey look a snackbar",
//                actionLabel = "Hide",
//                duration = SnackbarDuration.Short
//            )
//        }
        Surface {
            if (showDialog.value == Type.SnackBar) {
                SnackbarHost(
                    hostState = snackBarHostState.value,
                    snackbar = {
                        Snackbar(
                            action = {
                                TextButton(onClick = {
                                    snackBarHostState.value.currentSnackbarData?.dismiss()
                                }) {
                                    Text(
                                        text = "Hide",
                                    )
                                }
                            }
                        ) {
                            Text("hey look a snackbar")
                        }
                    }
                )
            }
        }
    }

    private fun onLaunchTextExamples() {
        Toast.makeText(this@MainActivity, "Not implemented yet...", Toast.LENGTH_SHORT).show()
    }

    @Composable
    private fun MyApp(content: @Composable () -> Unit) {
        JetpackComposeBasicsTheme {
            Surface(color = MaterialTheme.colors.background) {
                content()
            }
        }
    }

    @Composable
    private fun MyScreenContent(fields: List<Pair<String, Type>>) {
        LazyColumn {
            items(items = fields) { nextField ->
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .semantics { contentDescription = nextField.first }
                        .clickable { showDialog.value = nextField.second }
                        .fillMaxHeight(),
                ) {
                    val annotatedString = annotateMyString(nextField.first)
                    Text(text = annotatedString)
                    Divider(modifier = Modifier.padding(8.dp))
                }
            }
        }
    }

    @Composable
    private fun annotateMyString(text: String): AnnotatedString {
        val annotatedString = buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                withStyle(style = SpanStyle(color = Color(108, 196, 23, 255))) {
                    append("[Start] ")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color(253, 208, 23, 255)
                    )
                ) {
                    append(text)
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color(113, 24, 196, 255)
                    )
                ) {
                    append("[End]")
                }
            }
        }
        return annotatedString
    }
}