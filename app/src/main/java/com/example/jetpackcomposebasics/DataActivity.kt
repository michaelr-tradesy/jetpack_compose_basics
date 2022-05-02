package com.example.jetpackcomposebasics

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class DataActivity :  DefaultActivity() {

    data class UserInfo(
        val name: String,
        val contentDescription: String,
        val location: String,
        val body: String
    )

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
    @Composable
    override fun DefaultPreview() {
        MyApp {
            val list = mutableListOf<UserInfo>()

            for (i in 0..100) {
                list.add(
                    UserInfo(
                        "Coach Roebuck",
                        "Coach Roebuck's Profile Image: African American Flag",
                        "Salt Lake City, UT",
                        "Now we can change the background of the message content based on isExpanded " +
                                "when we click on a message. We will use the clickable modifier to handle " +
                                "click events on the composable. Instead of just toggling the background " +
                                "color of the Surface, we will animate the background color by gradually " +
                                "modifying its value from MaterialTheme.colors.surface to MaterialTheme.colors.primary" +
                                " and vice versa. To do so, we will use the animateColorAsState function. " +
                                "Lastly, we will use the animateContentSize modifier to animate the message container size smoothly:"
                    )
                )
            }
            MyScreenContent(list)
        }
    }

    @Composable
    private fun MyScreenContent(userInfo: List<UserInfo>) {
        LazyColumn {
            items(userInfo) { nextUserInfo ->
                Greeting(nextUserInfo)
            }
        }
    }

    @Composable
    fun Greeting(userInfo: UserInfo) {
        Row(modifier = modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(android.R.drawable.ic_delete),
                contentDescription = userInfo.contentDescription,
                modifier = modifier
                    // Set image size to 40 dp
                    .size(40.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )

            // Add a horizontal space between the image and the column
            Spacer(modifier = modifier.width(8.dp))

            // We keep track if the message is expanded or not in this
            // variable
            var isExpanded by remember { mutableStateOf(false) }

            // surfaceColor will be updated gradually from one color to the other
            val surfaceColor: Color by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.onSecondary else MaterialTheme.colors.surface,
            )

            // We toggle the isExpanded variable when we click on this Column
            Column(modifier = modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = userInfo.name,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle1
                )
                // Add a vertical space between the author and message texts
                Spacer(modifier = modifier.height(4.dp))

                Text(
                    text = userInfo.location,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )
                // Add a vertical space between the author and message texts
                Spacer(modifier = modifier.height(4.dp))

                Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                    Text(
                        text = userInfo.body,
                        // surfaceColor color will be changing gradually from primary to surface
                        color = surfaceColor,
                        // animateContentSize will change the Surface size gradually
                        modifier = modifier
                            .animateContentSize()
                            .padding(1.dp),
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}
