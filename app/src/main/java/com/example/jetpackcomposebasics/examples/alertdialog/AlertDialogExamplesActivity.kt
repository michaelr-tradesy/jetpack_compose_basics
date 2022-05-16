package com.example.jetpackcomposebasics.examples.alertdialog

import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.AppThemeState
import com.example.jetpackcomposebasics.DefaultActivity

@ExperimentalUnitApi
class AlertDialogExamplesActivity : DefaultActivity() {

    enum class ActionType {
        Idle, FirstAlertDialog, SecondAlertDialog;

        companion object {
            fun valueOf(value: Int): ActionType {
                values().map {
                    if( it.ordinal == value ) {
                        return it
                    }
                }
                return Idle
            }
        }
    }

    private val action = mutableStateOf(ActionType.Idle)
    private val key = "action"

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(key, action.value.ordinal)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        action.value = ActionType.valueOf(savedInstanceState.getInt(key))
    }

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
        when(action.value) {
            ActionType.FirstAlertDialog -> LaunchFirstAlertDialog()
            ActionType.SecondAlertDialog -> LaunchSecondAlertDialog()
            ActionType.Idle -> { }
        }
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = { action.value = ActionType.FirstAlertDialog }
            ) {
                Text("First Alert Dialog")
            }
            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = { action.value = ActionType.SecondAlertDialog }
            ) {
                Text("Second Alert Dialog")
            }
        }
    }

    @Composable
    private fun LaunchFirstAlertDialog() {
        val openDialog = remember { mutableStateOf(true) }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "Title")
                },
                text = {
                    Text(
                        "This area typically contains the supportive text " +
                                "which presents the details regarding the Dialog's purpose."
                    )
                },
                buttons = {
                    Row(
                        modifier = modifier.padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                openDialog.value = false
                                action.value = ActionType.Idle
                            }
                        ) {
                            Text("Dismiss")
                        }
                    }
                }
            )
        }
    }

    @Composable
    private fun LaunchSecondAlertDialog() {
        val openDialog = remember { mutableStateOf(true) }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onCloseRequest.
                    openDialog.value = false
                },
                title = {
                    Text(text = "Title")
                },
                text = {
                    Text(
                        "This area typically contains the supportive text " +
                                "which presents the details regarding the Dialog's purpose."
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                            action.value = ActionType.Idle
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                            action.value = ActionType.Idle
                        }
                    ) {
                        Text("Dismiss")
                    }
                }
            )
        }
    }
}
