package com.example.jetpackcomposebasics.main

import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.FloatingActionButtonDefaults.elevation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposebasics.DefaultActivity
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@ExperimentalUnitApi
class MainActivity : DefaultActivity() {

    private enum class Type {
        None,
        Alert,
        BadgeBox,
        Button,
        Card,
        Checkbox,
        CircularProgress,
        CustomView,
        Divider,
        DropDownList,
        EditTextField,
        ExtendedFloatingActionButton,
        FloatingActionButton,
        List,
        LinearProgress,
        ModalBottomSheetLayout,
        ModalDrawer,
        RadioButton,
        Slider,
        RangeSlider,
        Surface,
        TopAppBar,
        TextField,
        Switch,
    }

    private lateinit var scaffoldState: ScaffoldState
    private lateinit var coroutineScope: CoroutineScope
    private lateinit var clickCount: MutableState<Int>
    private lateinit var animatedProgress: Animatable<Float, AnimationVector1D>
    private lateinit var viewModel: MainViewModel

    private val openDialog = mutableStateOf(false)
    private val exampleType = mutableStateOf(Type.None)

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
        viewModel = DefaultMainViewModel()
        // Consider negative values to mean 'cut corner' and positive values to mean 'round corner'
        val sharpEdgePercent = -50f
        val roundEdgePercent = 45f
        // Start with sharp edges
        val animatedProgress = remember { Animatable(sharpEdgePercent) }
        // Create a coroutineScope for the animation
        coroutineScope = rememberCoroutineScope()
        // animation value to animate shape
        val progress = animatedProgress.value.roundToInt()

        // When progress is 0, there is no modification to the edges so we are just drawing a rectangle.
        // This allows for a smooth transition between cut corners and round corners.
        val fabShape = when {
            progress < 0 -> {
                CutCornerShape(kotlin.math.abs(progress))
            }
            progress == roundEdgePercent.toInt() -> {
                CircleShape
            }
            else -> {
                RoundedCornerShape(progress)
            }
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
        coroutineScope = rememberCoroutineScope()
        clickCount = remember { mutableStateOf(0) }

        MyApp {
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
                topBar = { TopAppBar(title = { Text("Scaffold ${exampleType.value}") }) },
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

                    Text(
                        modifier = Modifier.padding(16.dp).clickable { showToastExample() },
                        text = "Drawer title"
                    )
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
                        Pair("Alert Dialog Examples", Type.Alert),
                        Pair("Top App Bar Examples", Type.TopAppBar),
                        Pair("Badge Box Examples", Type.BadgeBox),
                        Pair("Button Examples", Type.Button),
                        Pair("Card Examples", Type.Card),
                        Pair("Checkbox Examples", Type.Checkbox),
                        Pair("Circular Progress Examples", Type.CircularProgress),
                        Pair("Custom View Examples", Type.CustomView),
                        Pair("Divider Examples", Type.Divider),
                        Pair("Drop Down List Examples", Type.DropDownList),
                        Pair("Edit Text Field Examples", Type.EditTextField),
                        Pair("Extended Floating Action Button Examples", Type.ExtendedFloatingActionButton),
                        Pair("Floating Action Button Examples", Type.FloatingActionButton),
                        Pair("Linear Progress Examples", Type.LinearProgress),
                        Pair("List of Buttons Examples", Type.List),
                        Pair("Modal Bottom Sheet Layout Examples", Type.ModalBottomSheetLayout),
                        Pair("Modal Drawer Examples", Type.ModalDrawer),
                        Pair("Radio Button Examples", Type.RadioButton),
                        Pair("Slider Examples", Type.Slider),
                        Pair("Range Slider Examples", Type.RangeSlider),
                        Pair("Surface Examples", Type.Surface),
                        Pair("Text Field Examples", Type.TextField),
                        Pair("Switch Examples", Type.Switch),
                        Pair("", Type.None),
                    )
                ShowAlertDialogExample()
                MyScreenContent(list)
            }
        }
    }

    @Composable
    fun BuildFloatingActionButton() {
        ExtendedFloatingActionButton(
            modifier = Modifier,
            elevation = elevation(),
            onClick = {
                coroutineScope.launch {
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
                        .clickable {
                            exampleType.value = nextField.second
                            onListItemSelected(nextField.second)
                        }
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

    @Composable
    private fun ShowAlertDialogExample() {
        Column {
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Dialog Title")
                    },
                    text = {
                        Text("Here is a text ")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("This is the Confirm Button")
                        }
                    },
                    dismissButton = {
                        Button(

                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("This is the dismiss Button")
                        }
                    }
                )
            }
        }
    }

    private fun onListItemSelected(type: Type) {
        when(type) {
            Type.Alert -> openDialog.value = !openDialog.value
            Type.None -> showToastExample()
            Type.TopAppBar -> showToastExample()
            Type.BadgeBox -> showToastExample()
            Type.Button -> viewModel.showButtonExamples(this)
            Type.Card -> showToastExample()
            Type.Checkbox -> showToastExample()
            Type.CircularProgress -> showToastExample()
            Type.CustomView -> showToastExample()
            Type.Divider -> viewModel.showDividerExamples(context = this.applicationContext)
            Type.DropDownList -> showToastExample()
            Type.EditTextField -> showToastExample()
            Type.ExtendedFloatingActionButton -> viewModel.showExtendedFloatingActionButtonExamples(context = this.applicationContext)
            Type.FloatingActionButton -> viewModel.showFloatingActionButtonExamples(context = this.applicationContext)
            Type.LinearProgress -> showToastExample()
            Type.List -> viewModel.showListExamples(this)
            Type.ModalBottomSheetLayout -> showToastExample()
            Type.ModalDrawer -> showToastExample()
            Type.RadioButton -> viewModel.showRadioButtonExamples(context = this.applicationContext)
            Type.Slider -> showToastExample()
            Type.RangeSlider -> showToastExample()
            Type.Surface -> showToastExample()
            Type.TextField -> showToastExample()
            Type.Switch -> showToastExample()
        }
    }
}