package com.example.jetpackcomposebasics.examples.switchbutton

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.DefaultActivity
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme


@ExperimentalUnitApi
class SwitchExamplesActivity : DefaultActivity() {

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
            SimpleSwitch()
            EnabledSwitch()
            DisabledSwitch()
            CheckedThumbColorSwitch()
        }
    }

    @Composable
    fun SimpleSwitch() {
        val mRemember = remember { mutableStateOf(false) }

        Row(modifier = modifier.padding(8.dp)) {
            Switch(checked = mRemember.value, onCheckedChange = { mRemember.value = it })
            Text(text = "Simple Switch")
        }
    }

    @Composable
    fun EnabledSwitch() {
        val mRemember = remember { mutableStateOf(false) }

        Row(modifier = modifier.padding(8.dp)) {
            Switch(
                checked = mRemember.value,
                onCheckedChange = { mRemember.value = it },
                enabled = true
            )
            Text(text = "Enabled Switch")
        }
    }

    @Composable
    fun DisabledSwitch() {
        val mRemember = remember { mutableStateOf(false) }

        Row(modifier = modifier.padding(8.dp)) {
            Switch(
                checked = mRemember.value,
                onCheckedChange = { mRemember.value = it },
                enabled = false
            )
            Text(text = "Disabled Switch")
        }
    }

    @Composable
    fun CheckedThumbColorSwitch() {
        val mRemember = remember { mutableStateOf(false) }

        Row(modifier = modifier.padding(8.dp)) {
            Switch(
                checked = mRemember.value,
                onCheckedChange = { mRemember.value = it },
                enabled = true,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Magenta
                ),
                modifier = modifier.padding(8.dp)
            )
            Text(text = "Checked Thumb Color Switch")
        }
    }
}