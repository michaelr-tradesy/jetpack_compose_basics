package com.example.jetpackcomposebasics.main

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.FloatingActionButtonDefaults.elevation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.DefaultActivity
import com.example.jetpackcomposebasics.buttonexamples.ButtonExamplesViewModel
import com.example.jetpackcomposebasics.buttonexamples.DefaultButtonExamplesViewModel
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme


@ExperimentalUnitApi
class ExtendedFloatingActionButtonExamplesActivity : DefaultActivity() {

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
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DefaultExtendedFloatingActionButtonComponent()
            ColoredExtendedFloatingActionButtonComponent()
            TextExtendedFloatingActionButtonComponent()
            IconExtendedFloatingActionButtonComponent()
            ShapeFloatingActionButtonComponent()
            ElevatedFloatingActionButtonComponent()
        }
    }

    @Composable
    private fun DefaultExtendedFloatingActionButtonComponent() {
        CreateFloatingActionButton(modifier = Modifier)
    }

    @Composable
    private fun TextExtendedFloatingActionButtonComponent() {
        CreateFloatingActionButton(
            modifier = Modifier.padding(4.dp),
            backgroundColor = Color.Yellow,
            contentColor = Color.Black,
            icon = Icons.Filled.Favorite,
            tint = Color.Black,
            text = "Text Extended FAB"
        )
    }

    @Composable
    private fun ColoredExtendedFloatingActionButtonComponent() {
        CreateFloatingActionButton(
            modifier = Modifier.padding(4.dp),
            backgroundColor = Color.Yellow,
            contentColor = Color.Black,
            text = "Colored"
        )
    }

    @Composable
    private fun IconExtendedFloatingActionButtonComponent() {
        CreateFloatingActionButton(
            modifier = Modifier.padding(2.dp),
            backgroundColor = Color.LightGray,
            contentColor = Color.Red,
            text = "Icon",
            icon = Icons.Default.ShoppingCart,
            tint = Color.Red
        )
    }

    @Composable
    private fun ShapeFloatingActionButtonComponent() {
        CreateFloatingActionButton(
            modifier = Modifier.padding(2.dp),
            backgroundColor = Color.Red,
            contentColor = Color.White,
            text = "Shape",
            icon = Icons.Default.Refresh,
            shape = RectangleShape,
            tint = Color.White
        )
    }

    @Composable
    private fun ElevatedFloatingActionButtonComponent() {
        CreateFloatingActionButton(
            modifier = Modifier.padding(2.dp),
            contentColor = Color.Black,
            text = "Elevated",
            icon = Icons.Default.Share,
            shape = RoundedCornerShape(8.dp),
            elevation = elevation(8.dp, 12.dp),
            backgroundColor = Color.White,
            tint = Color.Red
        )
    }

    @Composable
    private fun CreateFloatingActionButton(
        modifier: Modifier = Modifier,
        text: String? = null,
        onClick: () -> Unit = { },
        tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
        icon: ImageVector? = null,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        shape: Shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
        backgroundColor: Color = MaterialTheme.colors.secondary,
        contentColor: Color = contentColorFor(backgroundColor),
        elevation: FloatingActionButtonElevation = elevation()
    ) {
        ExtendedFloatingActionButton(
            modifier = modifier,
            elevation = elevation,
            onClick = onClick,
            interactionSource = interactionSource,
            shape = shape,
            contentColor = contentColor,
            backgroundColor = backgroundColor,
            icon = { icon?.let { Icon(icon, contentDescription = text, tint = tint) } },
            text = { text?.let { Text(text = it) } }
        )
    }
}