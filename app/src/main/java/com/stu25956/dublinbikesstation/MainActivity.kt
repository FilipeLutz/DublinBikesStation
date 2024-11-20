package com.stu25956.dublinbikesstation

/**
 * Mobile Apps II - Assignment 6
 * Author: Filipe Lutz Mariano
 * Student Number: 25956
 * Lecturer: Eugene O'Regan
 *
 * This project is a mobile application developed as part of the Mobile Apps II course.
 * The app integrates Firebase Authentication for user management and Google Maps API
 * for visualizing Dublin bike station locations. The project is inspired by a web app
 * created for the Full Stack / Back-End course under the guidance of lecturer John Rowley,
 * who also provided the "dublinbike.json" file used in this application.
 *
 * The app demonstrates the implementation of user authentication, geospatial API integration,
 * utilizing modern Android technologies like Jetpack Compose and Kotlin Coroutines.
 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.stu25956.dublinbikesstation.navigation.AppNavigation
import com.stu25956.dublinbikesstation.ui.theme.DublinBikesStationTheme
import com.stu25956.dublinbikesstation.view.LoginScreen

// main activity Class for the app
class MainActivity : ComponentActivity() {
    // onCreate function
    override fun onCreate(savedInstanceState: Bundle?) {
        // call super class onCreate function
        super.onCreate(savedInstanceState)
        // set content of the activity
        setContent {
            // call App function
            DublinBikesStationTheme {
                // call AppNavigation function
                AppNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DublinBikesStationTheme {
        LoginScreen(navController = rememberNavController())
    }
}