package com.example.jetpackcomposebasics.main

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.DefaultActivity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


/**
 * Source code was copied from here:
 * https://developer.android.com/jetpack/compose/animation
 */
class ButtonAnimationExamplesActivity : DefaultActivity() {

    @ExperimentalMaterialApi
    @Preview(
        fontScale = 1.5f,
        name = "Light Mode",
        uiMode = Configuration.UI_MODE_NIGHT_NO,
        showSystemUi = true,
        showBackground = true
    )
    @Preview(
        fontScale = 1.5f,
        name = "Dark Mode",
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showSystemUi = true,
        showBackground = true
    )
    @ExperimentalTransitionApi
    @ExperimentalAnimationApi
    @ExperimentalUnitApi
    @Composable
    override fun DefaultPreview() {
        MyApp {
            MyScreenContent()
        }
    }

    @ExperimentalMaterialApi
    @ExperimentalTransitionApi
    @ExperimentalAnimationApi
    @ExperimentalUnitApi
    @Composable
    private fun MyScreenContent() {
        val density = LocalDensity.current

        LazyColumn {
            items(1) {
                SimpleAnimatedVisibility()
                FadeInAnimatedVisibility(
                    text = "Baby Girl is Visible",
                    slideInVertically(
                        initialOffsetY = { with(density) { -40.dp.roundToPx() } }
                    ) + expandVertically(
                        expandFrom = Alignment.Top
                    ) + fadeIn(
                        initialAlpha = 0.3f
                    ))
                FadeInAnimatedVisibility(
                    text = "Coach Roebuck is Visible",
                    slideIn(
                        initialOffset = { fullSize -> IntOffset(fullSize.width, fullSize.height) }
                    ) + expandIn(
                        expandFrom = Alignment.Center
                    ) + fadeIn(
                        initialAlpha = 0.3f
                    ))
                MutableTransitionStateExample()
                AnimationForChildrenExample()
                CustomAnimationExample()
                AnimatedContentExample()
                AnimateContentSizeExample()
                CrossFadeExample()
                Gesture()
            }
        }
    }

    @ExperimentalAnimationApi
    @ExperimentalUnitApi
    @Composable
    private fun AnimationForChildrenExample() {
        var visible by remember { mutableStateOf(true) }
        Text(
            "Animation for Children: Click",
            modifier = Modifier.clickable { visible = !visible }
        )
        AnimatedVisibility(
            visible = visible,
            // Fade in/out the background and the foreground.
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray)
            ) {
                Box(
                    Modifier
                        .align(Alignment.Center)
                        .animateEnterExit(
                            // Slide in/out the inner box.
                            enter = slideInVertically(),
                            exit = slideOutVertically()
                        )
                        .sizeIn(minWidth = 256.dp, minHeight = 64.dp)
                        .background(Color.Red)
                ) {
                    Text(
                        "Animation for Children",
                        Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }
        }
    }

    @ExperimentalAnimationApi
    @ExperimentalUnitApi
    @Composable
    private fun FadeInAnimatedVisibility(text: String, enterTransition: EnterTransition) {
        var visible by remember { mutableStateOf(true) }

        Column {
            Text(
                "Fade In Animation: Click",
                modifier = Modifier.clickable { visible = !visible }
            )
            AnimatedVisibility(
                visible = visible,
                enter = enterTransition,
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Text(
                    text,
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }

    @ExperimentalAnimationApi
    @ExperimentalUnitApi
    @Composable
    private fun SimpleAnimatedVisibility() {
        var editable by remember { mutableStateOf(true) }
        Row(modifier = Modifier.clickable { editable = !editable }) {
            Text(text = "Simple Animation: Click ")
            AnimatedVisibility(visible = editable) {
                Text(text = "I'm Visible!")
            }
        }
    }

    @ExperimentalTransitionApi
    @ExperimentalAnimationApi
    @ExperimentalUnitApi
    @Composable
    private fun MutableTransitionStateExample() {
        // Create a MutableTransitionState<Boolean> for the AnimatedVisibility.
        val state = remember {
            MutableTransitionState(false).apply {
                // Start the animation immediately.
                targetState = true
            }
        }
        Row(modifier = Modifier.clickable { state.targetState = !state.targetState }) {
            Text(text = "Mutable Transition: Click ")
            AnimatedVisibility(visibleState = state) {
                Text(text = "I'm Visible!")
            }

            // Use the MutableTransitionState to know the current animation state
            // of the AnimatedVisibility.
            Text(
                text = when {
                    state.isIdle && state.currentState -> "Visible"
                    !state.isIdle && state.currentState -> "Disappearing"
                    state.isIdle && !state.currentState -> "Invisible"
                    else -> "Appearing"
                }
            )
        }
    }

    @ExperimentalTransitionApi
    @ExperimentalAnimationApi
    @ExperimentalUnitApi
    @Composable
    private fun CustomAnimationExample() {
        // Create a MutableTransitionState<Boolean> for the AnimatedVisibility.
        var visible by remember { mutableStateOf(true) }
        Row(modifier = Modifier.clickable { visible = !visible }) {
            Text(text = "Custom Animation: Click ")
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(),
                exit = fadeOut()
            ) { // this: AnimatedVisibilityScope
                // Use AnimatedVisibilityScope.transition() to add a custom animation
                // to the AnimatedVisibility.
                val background by transition.animateColor(label = "Background Animation Color") {
                        state ->
                    if (state == EnterExitState.Visible) Color.Blue else Color.Gray
                }
                Box(modifier = Modifier
                    .size(128.dp)
                    .background(background))
            }
        }
    }

    @ExperimentalMaterialApi
    @ExperimentalTransitionApi
    @ExperimentalAnimationApi
    @ExperimentalUnitApi
    @Composable
    private fun AnimatedContentExample() {
        var expanded by remember { mutableStateOf(false) }
        Surface(
            color = MaterialTheme.colors.primary,
            onClick = { expanded = !expanded }
        ) {
            AnimatedContent(
                targetState = expanded,
                transitionSpec = {
                    fadeIn(animationSpec = tween(150, 150)) with
                            fadeOut(animationSpec = tween(150)) using
                            SizeTransform { initialSize, targetSize ->
                                if (targetState) {
                                    keyframes {
                                        // Expand horizontally first.
                                        IntSize(targetSize.width, initialSize.height) at 150
                                        durationMillis = 300
                                    }
                                } else {
                                    keyframes {
                                        // Shrink vertically first.
                                        IntSize(initialSize.width, targetSize.height) at 150
                                        durationMillis = 300
                                    }
                                }
                            }
                }
            ) { targetExpanded ->
                if (targetExpanded) {
                    ExpandedContent()
                } else {
                    ContentIcon()
                }
            }
        }
    }

    @Composable
    private fun ExpandedContent() {
        Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
            Text(
                text = "The National Party had formed its first administration after the 1949 elections, in which it had ended four terms of government by the Labour Party.[1] The National government, with Sidney Holland as Prime Minister, had undertaken a number of economic and constitutional reforms, although it had not seriously modified the new social welfare system which Labour had introduced. Labour's leader, Peter Fraser, had died in December 1950 after a long period of poor health, and had been replaced in January 1951 by Walter Nash. Nash had been Minister of Finance for the duration of the first Labour government.[2]\n" +
                        "\n" +
                        "The most significant issue in the 1951 elections was the growing industrial unrest of the time, particularly the ongoing dockworkers dispute. Holland condemned the strikers, calling the situation \"industrial anarchy\". The Labour Party, under Nash, attempted to take a moderate position in the dispute, but ended up displeasing both sides. Holland, seeking a mandate to respond strongly to the strike, called a snap election. Another issue was high inflation, which frustrated voters and without the distraction of the strike, might have threatened Holland's government at the scheduled election for 1952.[3]",
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp),
                // If the message is expanded, we display all its content
                // otherwise we only display the first line
                style = MaterialTheme.typography.body2
            )
        }
    }

    @Composable
    private fun ContentIcon() {
        Image(
            painter = painterResource(android.R.drawable.ic_menu_info_details),
            contentDescription = "Content Icon",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
    }

    @Composable
    private fun AnimateContentSizeExample() {
        var count by remember { mutableStateOf(0)}
        var message by remember { mutableStateOf("Animate Content Size Example: Click") }
        Row(modifier = Modifier.clickable {
            message = "Clicked ${++count} times"
        }) {
            Box(
                modifier = Modifier.background(Color.Blue).animateContentSize()
            ) {
                Text(text = message)
            }
        }
    }

    @Composable
    private fun CrossFadeExample() {
        var currentPage by remember { mutableStateOf("A") }
        Row(modifier = Modifier.clickable {
            currentPage = if(currentPage == "A") "B" else "A"
        }) {
            Crossfade(targetState = currentPage) { screen ->
                when (screen) {
                    "A" -> Text("Page A")
                    "B" -> Text("Page B")
                }
            }
        }
    }

    @Composable
    fun Gesture() {
        val offset = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .swipeToDismiss { showToastExample() }
                .pointerInput(Unit) {
                    coroutineScope {
                        while (true) {
                            // Detect a tap event and obtain its position.
                            val position = awaitPointerEventScope {
                                awaitFirstDown().position
                            }
                            launch {
                                // Animate to the tap position.
                                offset.animateTo(position)
                            }
                        }
                    }
                }
        ) {
            Text(modifier = Modifier.offset { offset.value.toIntOffset() }, text = "Gesture: Tap me to move. Swipe me to dismiss.")
        }
    }

    private fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())

    fun Modifier.swipeToDismiss(
        onDismissed: () -> Unit
    ): Modifier = composed {
        val offsetX = remember { Animatable(0f) }
        pointerInput(Unit) {
            // Used to calculate fling decay.
            val decay = splineBasedDecay<Float>(this)
            // Use suspend functions for touch events and the Animatable.
            coroutineScope {
                while (true) {
                    // Detect a touch down event.
                    val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                    val velocityTracker = VelocityTracker()
                    // Stop any ongoing animation.
                    offsetX.stop()
                    awaitPointerEventScope {
                        horizontalDrag(pointerId) { change ->
                            // Update the animation value with touch events.
                            launch {
                                offsetX.snapTo(
                                    offsetX.value + change.positionChange().x
                                )
                            }
                            velocityTracker.addPosition(
                                change.uptimeMillis,
                                change.position
                            )
                        }
                    }
                    // No longer receiving touch events. Prepare the animation.
                    val velocity = velocityTracker.calculateVelocity().x
                    val targetOffsetX = decay.calculateTargetValue(
                        offsetX.value,
                        velocity
                    )
                    // The animation stops when it reaches the bounds.
                    offsetX.updateBounds(
                        lowerBound = -size.width.toFloat(),
                        upperBound = size.width.toFloat()
                    )
                    launch {
                        if (targetOffsetX.absoluteValue <= size.width) {
                            // Not enough velocity; Slide back.
                            offsetX.animateTo(
                                targetValue = 0f,
                                initialVelocity = velocity
                            )
                        } else {
                            // The element was swiped away.
                            offsetX.animateDecay(velocity, decay)
                            onDismissed()
                        }
                    }
                }
            }
        }
            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
    }
}