package com.example.jetpackcomposebasics.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.example.jetpackcomposebasics.buttonexamples.ButtonExamplesActivity
import com.example.jetpackcomposebasics.main.divider.DividerExamplesActivity
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
}

@ExperimentalUnitApi
class DefaultMainNavigator: MainNavigator {
    override fun showButtonExamples(context: Context) {
        val bundle = Bundle()
        navigate(
            applicationContext = context,
            clazz = ButtonExamplesActivity::class.java,
            bundle = bundle,
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        )
    }

    override fun showListExamples(context: Context) {
        val bundle = Bundle()
        navigate(
            applicationContext = context,
            clazz = ListOfButtonsExampleActivity::class.java,
            bundle = bundle,
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        )
    }

    override fun showDividerExamples(context: Context) {
        val bundle = Bundle()
        navigate(
            applicationContext = context,
            clazz = DividerExamplesActivity::class.java,
            bundle = bundle,
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        )
    }
}