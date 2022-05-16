package com.example.jetpackcomposebasics.examples.bottomsheetscaffold

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.AppThemeState
import com.example.jetpackcomposebasics.DefaultActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalUnitApi
class BottomSheetScaffoldExamplesActivity : DefaultActivity() {

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
        val scaffoldState = rememberBottomSheetScaffoldState()
        BottomSheetScaffold(
            sheetContent = {
                Box(
                    Modifier.fillMaxWidth().height(128.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Swipe up to expand sheet")
                }
                Column(
                    Modifier.fillMaxWidth().padding(64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Sheet content")
                    Spacer(Modifier.height(20.dp))
                    Button(
                        onClick = {
                            scope.launch { scaffoldState.bottomSheetState.collapse() }
                        }
                    ) {
                        Text("Click to collapse sheet")
                    }
                }
            },
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text("Bottom sheet scaffold") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Localized description")
                        }
                    }
                )
            },
            floatingActionButton = {
                var clickCount by remember { mutableStateOf(0) }
                FloatingActionButton(
                    onClick = {
                        // show snackbar as a suspend function
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Snackbar #${++clickCount}")
                        }
                    }
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Localized description")
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            sheetPeekHeight = 128.dp,
            drawerContent = {
                Column(
                    Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Drawer content")
                    Spacer(Modifier.height(20.dp))
                    Button(onClick = { scope.launch { scaffoldState.drawerState.close() } }) {
                        Text("Click to close drawer")
                    }
                }
            }
        ) { innerPadding ->
            val backgroundColors = backgroundColors(100)
            LazyColumn(contentPadding = innerPadding) {
                items(100) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(backgroundColors[it % backgroundColors.size])
                    )
                }
            }
        }
    }

    private fun backgroundColors(size: Int): List<Color> {
        val list = mutableListOf<Color>()

        for(i in 0 until size) {
            list.add(Color(Random.nextInt()))
        }
        return list.toList()
    }
}

