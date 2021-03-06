package com.example.jetpackcomposebasics.examples.divider

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.AppThemeState
import com.example.jetpackcomposebasics.DefaultActivity
import com.example.jetpackcomposebasics.examples.button.ButtonExamplesViewModel
import com.example.jetpackcomposebasics.examples.button.DefaultButtonExamplesViewModel


@ExperimentalUnitApi
class DividerExamplesActivity : DefaultActivity() {

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
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Not Yet Implemented...")
        }
    }

    @Composable
    private fun DividerComponent() {
        CreateDivider(modifier = modifier.padding(16.dp))
    }

    @Composable
    private fun ColoredDividerComponent() {
        CreateDivider(
            modifier = modifier,
            color = Color.Red
        )
    }

    @Composable
    private fun ThicknessDividerComponent() {
        CreateDivider(
            modifier = modifier,
            thickness = 4.dp
        )
    }

    @Composable
    private fun StartIndentDividerComponent() {
        CreateDivider(
            modifier = modifier,
            startIndent = 16.dp
        )
    }

    @Composable
    private fun AllInOneDividerComponent() {
        CreateDivider(
            modifier = modifier.padding(16.dp),
            color = Color.Blue,
            thickness = 2.dp,
            startIndent = 8.dp
        )
    }

    @Composable
    private fun CreateDivider(
        modifier: Modifier,
        color: Color = if(isSystemInDarkTheme()) Color.Black else Color.White,
        thickness: Dp = 1.dp,
        startIndent: Dp = 0.dp
    ) {
        Divider(
            modifier = modifier,
            color = color,
            thickness = thickness,
            startIndent = startIndent
        )
    }
}

