package com.example.jetpackcomposebasics.examples.floatingactionbutton

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.DefaultActivity
import com.example.jetpackcomposebasics.examples.button.ButtonExamplesViewModel
import com.example.jetpackcomposebasics.examples.button.DefaultButtonExamplesViewModel


@ExperimentalUnitApi
class FloatingActionButtonExamplesActivity : DefaultActivity() {

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
    private fun MyScreenContent() {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DefaultFloatingActionButtonComponent()
            ColoredFloatingActionButtonComponent()
            IconFloatingActionButtonComponent()
            ShapeFloatingActionButtonComponent()
            ElevatedFloatingActionButtonComponent()
        }
    }

    @Composable
    private fun DefaultFloatingActionButtonComponent() {
        CreateFloatingActionButton(modifier = modifier)
    }

    @Composable
    private fun ColoredFloatingActionButtonComponent() {
        CreateFloatingActionButton(
            modifier = modifier,
            backgroundColor = Color.Red
        )
    }

    @Composable
    private fun IconFloatingActionButtonComponent() {
        CreateFloatingActionButton(
            modifier = modifier.padding(8.dp),
            content = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Check Circle",
                    modifier = modifier.padding(8.dp),
                    tint = Color.Yellow
                )
            }
        )
    }

    @Composable
    private fun ShapeFloatingActionButtonComponent() {
        CreateFloatingActionButton(
            shape = RectangleShape,
            modifier = modifier.padding(16.dp),
            backgroundColor = Color.Blue,
            content = {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Call",
                    modifier = modifier.padding(8.dp),
                    tint = Color.White
                )
            }
        )
    }

    @Composable
    private fun ElevatedFloatingActionButtonComponent() {
        CreateFloatingActionButton(
            modifier = modifier.padding(16.dp),
            backgroundColor = Color.White,
            content = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Shopping Cart",
                    modifier = modifier.padding(8.dp),
                    tint = Color.Red
                )
            }
        )
    }

    @Composable
    private fun CreateFloatingActionButton(
        modifier: Modifier,
        onClick: () -> Unit = { },
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        shape: Shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
        backgroundColor: Color = MaterialTheme.colors.secondary,
        contentColor: Color = contentColorFor(backgroundColor),
        elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
        content: @Composable () -> Unit = { }
    ) {
        FloatingActionButton(
            onClick = onClick,
                    modifier = modifier,
                    interactionSource = interactionSource,
                    shape = shape,
                    backgroundColor = backgroundColor,
                    contentColor = contentColor,
                    elevation = elevation,
                    content = content
        )
    }
}
