package com.example.jetpackcomposebasics

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
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


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalUnitApi
enum class ComposeFeatureType(val resourceId: Int, val clazz: Class<*>) {
    Alert(R.string.Alert, AlertExamplesActivity::class.java),
    AlertDialog(R.string.AlertDialog, AlertDialogExamplesActivity::class.java),
    Animations(R.string.Animations, AnimationsExamplesActivity::class.java),                     // androidx.compose.animation.*
    BackdropScaffold(R.string.BackdropScaffold, BackdropScaffoldExamplesActivity::class.java),
    Badge(R.string.Badge, BadgeExamplesActivity::class.java),
    BadgedBox(R.string.BadgedBox, BadgedBoxExamplesActivity::class.java),
    BottomAppBar(R.string.BottomAppBar, BottomAppBarExamplesActivity::class.java),
    BottomDrawer(R.string.BottomDrawer, BottomDrawerExamplesActivity::class.java),
    BottomNavigation(R.string.BottomNavigation, BottomNavigationExamplesActivity::class.java),
    BottomSheetScaffold(R.string.BottomSheetScaffold, BottomSheetScaffoldExamplesActivity::class.java),
    Button(R.string.Button, ButtonExamplesActivity::class.java),
    ButtonAnimation(R.string.ButtonAnimation, ButtonAnimationExamplesActivity::class.java),
    Card(R.string.Card, CardExamplesActivity::class.java),
    Checkbox(R.string.Checkbox, CheckboxExamplesActivity::class.java),
    Chip(R.string.Chip, ChipExamplesActivity::class.java),
    CustomView(R.string.CustomView, CustomViewExamplesActivity::class.java),
    Divider(R.string.Divider, DividerExamplesActivity::class.java),
    DropdownMenu(R.string.DropdownMenu, DropDownMenuExamplesActivity::class.java),
    DropdownMenuItem(R.string.DropdownMenuItem, DropDownMenuItemExamplesActivity::class.java),
    EditTextField(R.string.EditTextField, EditTextFieldExamplesActivity::class.java),
    ExposedDropdownMenuBox(R.string.ExposedDropdownMenuBox, ExposedDropDownMenuBoxExamplesActivity::class.java),
    ExtendedFloatingActionButton(R.string.ExtendedFloatingActionButton, ExtendedFloatingActionButtonExamplesActivity::class.java),
    FilterChip(R.string.FilterChip, FilterChipExamplesActivity::class.java),
    FloatingActionButton(R.string.FloatingActionButton, FloatingActionButtonExamplesActivity::class.java),
    Gestures(R.string.Gestures, GesturesExamplesActivity::class.java),                       // androidx.compose.foundation.gestures
    Icon(R.string.Icon, IconExamplesActivity::class.java),
    Interactions(R.string.Interactions, InteractionsExamplesActivity::class.java),                   // androidx.compose.foundation.interaction
    LeadingIconTab(R.string.LeadingIconTab, LeadingIconTabExamplesActivity::class.java),
    List(R.string.List, ListExamplesActivity::class.java),
    ListItem(R.string.ListItem, ListItemExamplesActivity::class.java),
    ModalBottomSheetLayout(R.string.ModalBottomSheetLayout, ModalBottomSheetLayoutExamplesActivity::class.java),
    ModalDrawer(R.string.ModalDrawer, ModalDrawerExamplesActivity::class.java),
    NavigationRail(R.string.NavigationRail, NavigationRailExamplesActivity::class.java),
    NavigationRailItem(R.string.NavigationRailItem, NavigationRailItemExamplesActivity::class.java),
    OutlinedTextField(R.string.OutlinedTextField, OutlinedTextFieldExamplesActivity::class.java),
    ProgressIndicator(R.string.ProgressIndicator, ProgressIndicatorExamplesActivity::class.java),
    ProvideTextStyle(R.string.ProvideTextStyle, ProvideTextStyleExamplesActivity::class.java),
    RadioButton(R.string.RadioButton, RadioButtonExamplesActivity::class.java),
    RangeSlider(R.string.RangeSlider, RangeSliderExamplesActivity::class.java),
    ScrollableTabRow(R.string.ScrollableTabRow, ScrollableTabRowExamplesActivity::class.java),
    Slider(R.string.Slider, SliderExamplesActivity::class.java),
    Snackbar(R.string.Snackbar, SnackbarExamplesActivity::class.java),
    SnackbarHost(R.string.SnackbarHost, SnackbarHostExamplesActivity::class.java),
    Surface(R.string.Surface, SurfaceExamplesActivity::class.java),
    SwipeToDismiss(R.string.SwipeToDismiss, SwipeToDismissExamplesActivity::class.java),
    Switch(R.string.Switch, SwitchExamplesActivity::class.java),
    Tab(R.string.Tab, TabExamplesActivity::class.java),
    TabRow(R.string.TabRow, TabRowExamplesActivity::class.java),
    Text(R.string.Text, TextExamplesActivity::class.java),
    TextField(R.string.TextField, TextFieldExamplesActivity::class.java),
    TopAppBar(R.string.TopAppBar, TopAppBarExamplesActivity::class.java),
    TriStateCheckbox(R.string.TriStateCheckbox, TriStateCheckBoxExamplesActivity::class.java),
    UiGraphics(R.string.UiGraphics, UiGraphicsExamplesActivity::class.java),                          // androidx.compose.ui.graphics
    UiUtil(R.string.UiUtil, UiUtilExamplesActivity::class.java)                              // androidx.compose.ui.util
}
