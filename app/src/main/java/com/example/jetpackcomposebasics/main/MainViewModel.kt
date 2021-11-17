package com.example.jetpackcomposebasics.main

import android.content.Context
import androidx.compose.ui.unit.ExperimentalUnitApi

interface MainViewModel {
    fun showButtonExamples(context: Context)
    fun showListExamples(context: Context)
    fun showDividerExamples(context: Context)

}

class DefaultMainViewModel @ExperimentalUnitApi constructor(
    private val navigator: MainNavigator = DefaultMainNavigator()
) : MainViewModel {
    override fun showButtonExamples(context: Context) {
        navigator.showButtonExamples(context)
    }

    override fun showListExamples(context: Context) {
        navigator.showListExamples(context)
    }

    override fun showDividerExamples(context: Context) {
        navigator.showDividerExamples(context)
    }
}