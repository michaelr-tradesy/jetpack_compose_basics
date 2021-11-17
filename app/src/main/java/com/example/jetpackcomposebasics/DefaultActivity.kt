package com.example.jetpackcomposebasics

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView

abstract class DefaultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComposeView(this).also {
            setContentView(it)
        }.setContent {
            DefaultPreview()
        }
    }

    protected fun showToastExample() {
        Toast.makeText(this, "Toast Example...", Toast.LENGTH_SHORT).show()
    }

    @Composable
    abstract fun DefaultPreview()
}
