package com.example.jetpackcomposebasics.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.example.jetpackcomposebasics.buttonexamples.ButtonExamplesActivity
import com.example.jetpackcomposebasics.main.divider.DividerExamplesActivity
import com.example.jetpackcomposebasics.main.floatingactionbuttons.FloatingActionButtonExamplesActivity
import com.example.jetpackcomposebasics.main.listofbuttons.ListOfButtonsExampleActivity

interface MainNavigator {
    fun showButtonExamples(context: Context)

    fun <T> navigate(
        applicationContext: Context,
        clazz: Class<T>,
        flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK,
        bundle: Bundle? = null
    ) {
        val intent = Intent(applicationContext, clazz)
        intent.flags = flags
        bundle?.let { intent.putExtras(it) }
        applicationContext.startActivity(intent)
    }

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

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalUnitApi
class DefaultMainNavigator: MainNavigator {
    override fun showButtonExamples(context: Context) {
        navigate(applicationContext = context, clazz = ButtonExamplesActivity::class.java)
    }

    override fun showListExamples(context: Context) {
        navigate(applicationContext = context, clazz = ListOfButtonsExampleActivity::class.java)
    }

    override fun showDividerExamples(context: Context) {
        navigate(applicationContext = context, clazz = DividerExamplesActivity::class.java)
    }

    override fun showFloatingActionButtonExamples(context: Context) {
        navigate(applicationContext = context, clazz = FloatingActionButtonExamplesActivity::class.java)
    }

    override fun showExtendedFloatingActionButtonExamples(context: Context) {
        navigate(applicationContext = context, clazz = ExtendedFloatingActionButtonExamplesActivity::class.java)
    }

    override fun showRadioButtonExamples(context: Context) {
        navigate(applicationContext = context, clazz = RadioButtonExamplesActivity::class.java)
    }

    override fun showProgressIndicatorExamples(context: Context) {
        navigate(applicationContext = context, clazz = ProgressIndicatorExamplesActivity::class.java)
    }

    override fun showCheckboxExamples(context: Context) {
        navigate(applicationContext = context, clazz = CheckboxExamplesActivity::class.java)
    }

    override fun showSwitchExamples(context: Context) {
        navigate(applicationContext = context, clazz = SwitchExamplesActivity::class.java)
    }

    override fun showSliderExamples(context: Context) {
        navigate(applicationContext = context, clazz = SliderExamplesActivity::class.java)
    }

    override fun showButtonAnimationExamples(context: Context) {
        navigate(applicationContext = context, clazz = ButtonAnimationExamplesActivity::class.java)
    }

    private fun <T> navigate(
        applicationContext: Context,
        clazz: Class<T>,
    ) {
        val bundle = Bundle()
        navigate(
            applicationContext = applicationContext,
            clazz = clazz,
            bundle = bundle,
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        )
    }
}