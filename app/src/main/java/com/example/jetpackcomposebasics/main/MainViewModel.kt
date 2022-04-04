package com.example.jetpackcomposebasics.main

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi

interface MainViewModel {
    fun showButtonExamples(context: Context)
    fun showListExamples(context: Context)
    fun showDividerExamples(context: Context)
    fun showFloatingActionButtonExamples(context: Context)
    fun showExtendedFloatingActionButtonExamples(context: Context)
    fun showRadioButtonExamples(context: Context)
    fun showProgressIndicatorExamples(context: Context)
    fun showCheckboxExamples(context: Context)
    fun showSwitchExamples(context: Context)
    fun showSliderExamples(context: Context)
    fun showButtonAnimationExamples(context: Context)
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
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

    override fun showProgressIndicatorExamples(context: Context) {
        navigator.showProgressIndicatorExamples(context)
    }

    override fun showCheckboxExamples(context: Context) {
        navigator.showCheckboxExamples(context)
    }

    override fun showSwitchExamples(context: Context) {
        navigator.showSwitchExamples(context)
    }

    override fun showSliderExamples(context: Context) {
        navigator.showSliderExamples(context)
    }

    override fun showButtonAnimationExamples(context: Context) {
        navigator.showButtonAnimationExamples(context)
    }
}