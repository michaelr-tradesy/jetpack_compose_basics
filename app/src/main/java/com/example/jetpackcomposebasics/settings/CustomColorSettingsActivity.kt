package com.example.jetpackcomposebasics.settings

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.AppThemeState
import com.example.jetpackcomposebasics.ComposeFeatureType
import com.example.jetpackcomposebasics.DefaultActivity
import com.example.jetpackcomposebasics.R
import com.example.jetpackcomposebasics.main.DefaultMainViewModel
import com.example.jetpackcomposebasics.main.MainViewModel
import com.example.jetpackcomposebasics.ui.theme.ColorPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalUnitApi
class CustomColorSettingsActivity : DefaultActivity() {

    private lateinit var scaffoldState: ScaffoldState
    private lateinit var coroutineScope: CoroutineScope
    private lateinit var clickCount: MutableState<Int>
    private lateinit var viewModel: MainViewModel
    private lateinit var expanded: MutableState<Boolean>
    private lateinit var result: MutableState<String>


    private val openDialog = mutableStateOf(false)
    private val exampleType = mutableStateOf(ComposeFeatureType.Alert)

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
                CutCornerShape(abs(progress))
            }
            progress == roundEdgePercent.toInt() -> {
                CircleShape
            }
            else -> {
                RoundedCornerShape(progress)
            }
        }

        expanded = rememberSaveable { mutableStateOf(false) }
        result = rememberSaveable { mutableStateOf("") }
        scaffoldState = rememberScaffoldState()
        coroutineScope = rememberCoroutineScope()
        clickCount = rememberSaveable { mutableStateOf(0) }

        MyApp {
            Scaffold(
                scaffoldState = scaffoldState,
                snackbarHost = createSnackBarHost(),
                topBar = {
                    CreateTopBar()
                },
                bottomBar = {
                    CreateBottomBar(fabShape)
                },
                drawerContent = createDrawerContent(),
                floatingActionButton = {
                    BuildFloatingActionButton()
                },
                floatingActionButtonPosition = FabPosition.Center,
                drawerGesturesEnabled = true,
                isFloatingActionButtonDocked = true, content = createScaffoldContent(),
            )
        }
    }

    @Composable
    private fun createSnackBarHost(): @Composable (SnackbarHostState) -> Unit =
        {
            // reuse default SnackbarHost to have default animation and timing handling
            SnackbarHost(it) { data ->
                // custom snackbar with the custom border
                Snackbar(
                    modifier = modifier,
                    snackbarData = data
                )
            }
        }

    @Composable
    private fun CreateTopBar() {
        TopAppBar(
            title = { Text("${exampleType.value}") },
            navigationIcon = {
                // navigation icon is use
                // for drawer icon.
                IconButton(onClick = {
                    coroutineScope.launch { scaffoldState.drawerState.open() }
                }) {
                    Icon(Icons.Filled.Menu, "menu")
                }
            },
            actions = {
                val isDarkTheme = appThemeState.value.isDarkTheme
                val isSystemOverrideEnabled = appThemeState.value.isSystemOverrideEnabled

                IconToggleButton(
                    checked = isSystemOverrideEnabled,
                    onCheckedChange = {
                        newAppThemeState(appThemeState.value.copy(isSystemOverrideEnabled = it))
                        if (isSystemOverrideEnabled) {
                            result.value = getString(R.string.system_override_enabled)
                        } else {
                            result.value = getString(R.string.system_override_disabled)
                        }
                    }
                ) {
                    val tint by animateColorAsState(
                        if (isSystemOverrideEnabled) Color(0xFF7BB661)
                        else Color.LightGray
                    )
                    Icon(
                        Icons.Filled.Smartphone,
                        contentDescription = stringResource(id = R.string.system_override),
                        tint = tint
                    )
                }

                IconToggleButton(
                    checked = isDarkTheme,
                    onCheckedChange = {
                        newAppThemeState(appThemeState.value.copy(isDarkTheme = it))
                        if (isDarkTheme) {
                            result.value = getString(R.string.dark_mode_enabled)
                        } else {
                            result.value = getString(R.string.dark_mode_disabled)
                        }
                    }
                ) {
                    val tint by animateColorAsState(
                        if (isDarkTheme) Color(0xFF7BB661)
                        else Color.LightGray
                    )
                    Icon(
                        if (isDarkTheme) {
                            Icons.Filled.DarkMode
                        } else {
                            Icons.Filled.LightMode
                        },
                        contentDescription = stringResource(id = R.string.dark_mode),
                        tint = tint
                    )
                }

                Box(
                    Modifier
                        .wrapContentSize(Alignment.TopEnd)
                ) {
                    IconButton(onClick = {
                        expanded.value = true
                        result.value = "More icon clicked"
                    }) {
                        Icon(
                            Icons.Filled.MoreVert,
                            contentDescription = "Localized description"
                        )
                    }

                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                    ) {
                        LoadColorPaletteDropdownMenuItems()
                    }
                }
            }
        )
    }

    @Composable
    private fun CreateBottomBar(fabShape: CornerBasedShape) {
        BottomAppBar(cutoutShape = fabShape) {
            IconButton(
                onClick = {
                    coroutineScope.launch { scaffoldState.drawerState.open() }
                }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        }
    }

    @Composable
    private fun createDrawerContent(): @Composable() (ColumnScope.() -> Unit) =
        {
            Text(
                modifier = modifier
                    .padding(16.dp)
                    .clickable {
                        coroutineScope.launch { scaffoldState.drawerState.close() }
                        showToastExample()
                    },
                text = "Drawer Option 1"
            )
            Divider()
            Text(
                modifier = modifier
                    .padding(16.dp)
                    .clickable {
                        coroutineScope.launch { scaffoldState.drawerState.close() }
                        showToastExample()
                    },
                text = "Drawer Option 2"
            )
            Divider()
            Text(
                modifier = modifier
                    .padding(16.dp)
                    .clickable {
                        coroutineScope.launch { scaffoldState.drawerState.close() }
                        showToastExample()
                    },
                text = "Drawer Option 3"
            )
            Divider()
            // Drawer items
        }

    @Composable
    private fun createScaffoldContent(): @Composable (PaddingValues) -> Unit =
        {
            // Screen content
            // reference:
            // https://github.com/Foso/Jetpack-Compose-Playground/tree/master/mysamples/src/main/java/de/jensklingenberg/jetpackcomposeplayground/mysamples/github/material
            ShowAlertDialogExample()
            MyScreenContent()
        }

    @Composable
    private fun LoadColorPaletteDropdownMenuItems() {
        ColorPalette.values().map {
            DropdownMenuItem(onClick = {
                expanded.value = false
                result.value = it.toString()
                newAppThemeState(appThemeState.value.copy(colorPalette = it))
            }) {
                Text(it.toString())
            }

            Divider()
        }
    }

    @Composable
    fun BuildFloatingActionButton() {
        ExtendedFloatingActionButton(
            modifier = modifier,
            elevation = FloatingActionButtonDefaults.elevation(),
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
    private fun MyScreenContent() {
        LazyColumn {
            items(items = ComposeFeatureType.values()) { nextField ->
                val text = stringResource(id = nextField.resourceId)
                Column(
                    modifier = modifier
                        .semantics { contentDescription = text }
                        .clickable {
                            exampleType.value = nextField
                            onListItemSelected(nextField)
                        }
                        .fillMaxHeight(),
                ) {
                    Text(
                        modifier = modifier.padding(16.dp),
                        text = text
                    )
                    Divider(modifier = modifier)
                }
            }
        }
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

    private fun onListItemSelected(type: ComposeFeatureType) {
        viewModel.onFeatureSelected(this.applicationContext, type)
    }
}