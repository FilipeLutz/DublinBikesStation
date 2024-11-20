package com.stu25956.dublinbikesstation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stu25956.dublinbikesstation.auth.AuthHelper
import com.stu25956.dublinbikesstation.view.LoginScreen
import com.stu25956.dublinbikesstation.view.SignUpScreen
import com.stu25956.dublinbikesstation.view.StationDetailScreen
import com.stu25956.dublinbikesstation.view.StationListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (AuthHelper.isUserLoggedIn()) "stationList" else "login"
    ) {
        composable("login") {
            LoginScreen(navController)
        }
        composable("signup") {
            SignUpScreen(navController)
        }
        composable("stationList") {
            StationListScreen(navController)
        }
        composable("detail/{stationId}") { backStackEntry ->
            val stationId = backStackEntry.arguments?.getString("stationId")?.toIntOrNull()
            // Pass the navController to StationDetailScreen
            stationId?.let { StationDetailScreen(it, navController) }
        }
    }
}
