package com.example.jetpackcomposebasics.main

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.example.jetpackcomposebasics.ComposeFeatureType


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalUnitApi
interface MainViewModel {
    fun onFeatureSelected(context: Context, type: ComposeFeatureType)
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalUnitApi
class DefaultMainViewModel @ExperimentalUnitApi constructor(
    private val navigator: MainNavigator = DefaultMainNavigator()
) : MainViewModel {
    override fun onFeatureSelected(context: Context, type: ComposeFeatureType) {
        navigator.onFeatureSelected(context, type)
    }
}