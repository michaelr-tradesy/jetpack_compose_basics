package com.example.jetpackcomposebasics.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.example.jetpackcomposebasics.ComposeFeatureType
import com.example.jetpackcomposebasics.ComposeFeatureType.*
import com.example.jetpackcomposebasics.ComposeFeatureType.List
import com.example.jetpackcomposebasics.examples.alert.AlertExamplesActivity
import com.example.jetpackcomposebasics.examples.alertdialog.AlertDialogExamplesActivity
import com.example.jetpackcomposebasics.examples.animations.AnimationsExamplesActivity
import com.example.jetpackcomposebasics.examples.backdropscaffold.BackdropScaffoldExamplesActivity
import com.example.jetpackcomposebasics.examples.badge.BadgeExamplesActivity
import com.example.jetpackcomposebasics.examples.badgedbox.BadgedBoxExamplesActivity
import com.example.jetpackcomposebasics.examples.bottomappbar.BottomAppBarExamplesActivity
import com.example.jetpackcomposebasics.examples.bottomdrawer.BottomDrawerExamplesActivity
import com.example.jetpackcomposebasics.examples.bottomnavigation.BottomNavigationExamplesActivity
import com.example.jetpackcomposebasics.examples.bottomsheetscaffold.BottomSheetScaffoldExamplesActivity
import com.example.jetpackcomposebasics.examples.button.ButtonExamplesActivity
import com.example.jetpackcomposebasics.examples.buttonanimation.ButtonAnimationExamplesActivity
import com.example.jetpackcomposebasics.examples.card.CardExamplesActivity
import com.example.jetpackcomposebasics.examples.checkbox.CheckboxExamplesActivity
import com.example.jetpackcomposebasics.examples.chip.ChipExamplesActivity
import com.example.jetpackcomposebasics.examples.customview.CustomViewExamplesActivity
import com.example.jetpackcomposebasics.examples.divider.DividerExamplesActivity
import com.example.jetpackcomposebasics.examples.dropdownmenu.DropDownMenuExamplesActivity
import com.example.jetpackcomposebasics.examples.dropdownmenuitem.DropDownMenuItemExamplesActivity
import com.example.jetpackcomposebasics.examples.edittextfield.EditTextFieldExamplesActivity
import com.example.jetpackcomposebasics.examples.exposeddropdownmenubox.ExposedDropDownMenuBoxExamplesActivity
import com.example.jetpackcomposebasics.examples.extendedfloatingactionbutton.ExtendedFloatingActionButtonExamplesActivity
import com.example.jetpackcomposebasics.examples.filterchip.FilterChipExamplesActivity
import com.example.jetpackcomposebasics.examples.floatingactionbutton.FloatingActionButtonExamplesActivity
import com.example.jetpackcomposebasics.examples.gestures.GesturesExamplesActivity
import com.example.jetpackcomposebasics.examples.icon.IconExamplesActivity
import com.example.jetpackcomposebasics.examples.interactions.InteractionsExamplesActivity
import com.example.jetpackcomposebasics.examples.leadingicontab.LeadingIconTabExamplesActivity
import com.example.jetpackcomposebasics.examples.list.ListExamplesActivity
import com.example.jetpackcomposebasics.examples.listitem.ListItemExamplesActivity
import com.example.jetpackcomposebasics.examples.modalbottomsheetlayout.ModalBottomSheetLayoutExamplesActivity
import com.example.jetpackcomposebasics.examples.modaldrawer.ModalDrawerExamplesActivity
import com.example.jetpackcomposebasics.examples.navigationrail.NavigationRailExamplesActivity
import com.example.jetpackcomposebasics.examples.navigationrailitem.NavigationRailItemExamplesActivity
import com.example.jetpackcomposebasics.examples.outlinedtextfield.OutlinedTextFieldExamplesActivity
import com.example.jetpackcomposebasics.examples.progressindicator.ProgressIndicatorExamplesActivity
import com.example.jetpackcomposebasics.examples.providetextstyle.ProvideTextStyleExamplesActivity
import com.example.jetpackcomposebasics.examples.radiobutton.RadioButtonExamplesActivity
import com.example.jetpackcomposebasics.examples.rangeslider.RangeSliderExamplesActivity
import com.example.jetpackcomposebasics.examples.scrollabletabrow.ScrollableTabRowExamplesActivity
import com.example.jetpackcomposebasics.examples.slider.SliderExamplesActivity
import com.example.jetpackcomposebasics.examples.snackbar.SnackbarExamplesActivity
import com.example.jetpackcomposebasics.examples.snackbarhost.SnackbarHostExamplesActivity
import com.example.jetpackcomposebasics.examples.surface.SurfaceExamplesActivity
import com.example.jetpackcomposebasics.examples.swipetodismiss.SwipeToDismissExamplesActivity
import com.example.jetpackcomposebasics.examples.switchbutton.SwitchExamplesActivity
import com.example.jetpackcomposebasics.examples.tab.TabExamplesActivity
import com.example.jetpackcomposebasics.examples.tabrow.TabRowExamplesActivity
import com.example.jetpackcomposebasics.examples.text.TextExamplesActivity
import com.example.jetpackcomposebasics.examples.textfield.TextFieldExamplesActivity
import com.example.jetpackcomposebasics.examples.topappbar.TopAppBarExamplesActivity
import com.example.jetpackcomposebasics.examples.tristatecheckbox.TriStateCheckBoxExamplesActivity
import com.example.jetpackcomposebasics.examples.uigraphics.UiGraphicsExamplesActivity
import com.example.jetpackcomposebasics.examples.uiutil.UiUtilExamplesActivity

interface MainNavigator {
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

    fun onFeatureSelected(context: Context, type: ComposeFeatureType)
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalUnitApi
class DefaultMainNavigator: MainNavigator {
    override fun onFeatureSelected(context: Context, type: ComposeFeatureType) {
        val clazz = when(type) {
            None -> TODO()
            Alert -> AlertExamplesActivity::class.java
            AlertDialog -> AlertDialogExamplesActivity::class.java
            Animations -> AnimationsExamplesActivity::class.java
            BackdropScaffold -> BackdropScaffoldExamplesActivity::class.java
            Badge -> BadgeExamplesActivity::class.java
            BadgedBox -> BadgedBoxExamplesActivity::class.java
            BottomAppBar -> BottomAppBarExamplesActivity::class.java
            BottomDrawer -> BottomDrawerExamplesActivity::class.java
            BottomNavigation -> BottomNavigationExamplesActivity::class.java
            BottomSheetScaffold -> BottomSheetScaffoldExamplesActivity::class.java
            Button -> ButtonExamplesActivity::class.java
            ButtonAnimation -> ButtonAnimationExamplesActivity::class.java
            Card -> CardExamplesActivity::class.java
            Checkbox -> CheckboxExamplesActivity::class.java
            Chip -> ChipExamplesActivity::class.java
            CustomView -> CustomViewExamplesActivity::class.java
            Divider -> DividerExamplesActivity::class.java
            DropdownMenu -> DropDownMenuExamplesActivity::class.java
            DropdownMenuItem -> DropDownMenuItemExamplesActivity::class.java
            EditTextField -> EditTextFieldExamplesActivity::class.java
            ExposedDropdownMenuBox -> ExposedDropDownMenuBoxExamplesActivity::class.java
            ExtendedFloatingActionButton -> ExtendedFloatingActionButtonExamplesActivity::class.java
            FilterChip -> FilterChipExamplesActivity::class.java
            FloatingActionButton -> FloatingActionButtonExamplesActivity::class.java
            Gestures -> GesturesExamplesActivity::class.java
            Icon -> IconExamplesActivity::class.java
            Interactions -> InteractionsExamplesActivity::class.java
            LeadingIconTab -> LeadingIconTabExamplesActivity::class.java
            List -> ListExamplesActivity::class.java
            ListItem -> ListItemExamplesActivity::class.java
            ModalBottomSheetLayout -> ModalBottomSheetLayoutExamplesActivity::class.java
            ModalDrawer -> ModalDrawerExamplesActivity::class.java
            NavigationRail -> NavigationRailExamplesActivity::class.java
            NavigationRailItem -> NavigationRailItemExamplesActivity::class.java
            OutlinedTextField -> OutlinedTextFieldExamplesActivity::class.java
            ProgressIndicator -> ProgressIndicatorExamplesActivity::class.java
            ProvideTextStyle -> ProvideTextStyleExamplesActivity::class.java
            RadioButton -> RadioButtonExamplesActivity::class.java
            RangeSlider -> RangeSliderExamplesActivity::class.java
            ScrollableTabRow -> ScrollableTabRowExamplesActivity::class.java
            Slider -> SliderExamplesActivity::class.java
            Snackbar -> SnackbarExamplesActivity::class.java
            SnackbarHost -> SnackbarHostExamplesActivity::class.java
            Surface -> SurfaceExamplesActivity::class.java
            SwipeToDismiss -> SwipeToDismissExamplesActivity::class.java
            Switch -> SwitchExamplesActivity::class.java
            Tab -> TabExamplesActivity::class.java
            TabRow -> TabRowExamplesActivity::class.java
            Text -> TextExamplesActivity::class.java
            TextField -> TextFieldExamplesActivity::class.java
            TopAppBar -> TopAppBarExamplesActivity::class.java
            TriStateCheckbox -> TriStateCheckBoxExamplesActivity::class.java
            UiGraphics -> UiGraphicsExamplesActivity::class.java
            UiUtil -> UiUtilExamplesActivity::class.java
        }
        navigate(context = context, clazz = clazz)
    }

    private fun <T> navigate(
        context: Context,
        clazz: Class<T>,
    ) {
        val bundle = Bundle()
        navigate(
            applicationContext = context,
            clazz = clazz,
            bundle = bundle,
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        )
    }
}