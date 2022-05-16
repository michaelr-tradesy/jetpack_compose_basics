package com.example.jetpackcomposebasics.examples.backdropscaffold

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.AppThemeState
import com.example.jetpackcomposebasics.DefaultActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalUnitApi
class BackdropScaffoldExamplesActivity : DefaultActivity() {

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
        val scope = rememberCoroutineScope()
        val selection = remember { mutableStateOf(1) }
        val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
        LaunchedEffect(scaffoldState) {
            scaffoldState.reveal()
        }
        BackdropScaffold(
            modifier = modifier,
            gesturesEnabled = true,
            scaffoldState = scaffoldState,
            appBar = {
                TopAppBar(
                    title = { Text("Backdrop scaffold") },
                    navigationIcon = {
                        if (scaffoldState.isConcealed) {
                            IconButton(onClick = { scope.launch { scaffoldState.reveal() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Localized description")
                            }
                        } else {
                            IconButton(onClick = { scope.launch { scaffoldState.conceal() } }) {
                                Icon(Icons.Default.Close, contentDescription = "Localized description")
                            }
                        }
                    },
                    actions = {
                        var clickCount by remember { mutableStateOf(0) }
                        IconButton(
                            onClick = {
                                // show snackbar as a suspend function
                                scope.launch {
                                    scaffoldState.snackbarHostState
                                        .showSnackbar("Snackbar #${++clickCount}")
                                }
                            }
                        ) {
                            Icon(Icons.Default.Favorite, contentDescription = "Localized description")
                        }
                    },
                    elevation = 0.dp,
                    backgroundColor = Color.Transparent
                )
            },
            headerHeight = 100.dp,
            stickyFrontLayer = false,
            persistentAppBar = false,
            backLayerContent = {
                LazyColumn {
                    items(if (selection.value >= 3) 3 else 5) {
                        ListItem(
                            modifier.clickable {
                                selection.value = it
                                scope.launch { scaffoldState.conceal() }
                            },
                            text = { Text("Select $it") }
                        )
                    }
                }
            },
            frontLayerContent = {
                Text(
                    modifier = modifier.padding(8.dp),
                    text ="Selection: ${selection.value}"
                )
                LazyColumn {
                    items(50) {
                        ListItem(
                            text = { Text("Item $it") },
                            icon = {
                                Icon(
                                    Icons.Default.Favorite,
                                    contentDescription = "Localized description"
                                )
                            }
                        )
                    }
                }
            }
        )
    }
}