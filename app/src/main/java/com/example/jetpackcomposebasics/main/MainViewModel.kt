package com.example.jetpackcomposebasics.main

import android.content.Context
import androidx.compose.ui.unit.ExperimentalUnitApi

interface MainViewModel {
    fun showButtonExamples(context: Context)
    fun showListExamples(context: Context)
    fun showDividerExamples(context: Context)
    fun showFloatingActionButtonExamples(context: Context)
    fun showExtendedFloatingActionButtonExamples(context: Context)
    fun showRadioButtonExamples(context: Context)

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

    override fun showFloatingActionButtonExamples(context: Context) {
        navigator.showFloatingActionButtonExamples(context)
    }

    override fun showExtendedFloatingActionButtonExamples(context: Context) {
        navigator.showExtendedFloatingActionButtonExamples(context)
    }

    override fun showRadioButtonExamples(context: Context) {
        navigator.showRadioButtonExamples(context)
    }
}