package com.stu25956.dublinbikesstation

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