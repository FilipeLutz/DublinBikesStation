package com.stu25956.dublinbikesstation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.stu25956.dublinbikesstation.auth.AuthHelper
import com.stu25956.dublinbikesstation.data.StationRepository
import com.stu25956.dublinbikesstation.ui.theme.Black
import com.stu25956.dublinbikesstation.ui.theme.LightGray

// Station list screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StationListScreen(navController: NavHostController) {
    // Load stations from repository
    val stations = StationRepository.loadStations(LocalContext.current)
    var searchText by remember { mutableStateOf("") }
    val filteredStations = remember(searchText, stations) {
        // Filter stations by search text
        stations.filter { it.name.contains(searchText, ignoreCase = true) }
    }
    // User menu and logout dialog
    var showMenu by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }

    // Scaffold with top bar
    Scaffold(
        // Top bar
        topBar = {
            TopAppBar(
                // Title
                title = {
                    Text(
                        "Dublin Bike Stations",
                        modifier = Modifier
                            .padding(12.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                // User icon
                actions = {
                    Box(
                        Modifier
                            .padding(end = 22.dp)
                            .size(45.dp)
                    ) {
                        IconButton(
                            onClick = { showMenu = true })
                        {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "User Options",
                                tint = Black,
                                modifier = Modifier
                                    .size(35.dp)
                            )
                        }
                        // Dropdown menu
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            // Log out option
                            DropdownMenuItem(
                                onClick = {
                                    showMenu = false
                                    showLogoutDialog = true
                                },
                                // Log out
                                text = {
                                    Text(
                                        "Log Out",
                                        Modifier.padding(10.dp),
                                        fontSize = 20.sp,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        // Column with search box and station list
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(17.dp)
        ) {
            // Search box
            OutlinedTextField(
                value = searchText,
                // Update search text
                onValueChange = { searchText = it },
                // Search box label
                label = {
                    Text(
                        "Search Station",
                        fontSize = 18.sp
                    )
                },
                textStyle = TextStyle(
                    fontSize = 22.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))
            // Box for lazy column
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 8.dp)
            )
            {
                // Lazy column with station items
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                ) {
                    items(filteredStations)
                    { station ->
                        // Station item
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController
                                        .navigate("detail/${station.number}")
                                }
                                .padding(8.dp)
                        ) {
                            // Station name
                            Text(
                                text = station.name,
                                fontSize = 20.sp
                            )
                            // Station Name Divider
                            HorizontalDivider(
                                modifier = Modifier
                                    .padding(vertical = 4.dp),
                                color = LightGray
                                    .copy(alpha = 0.5f)
                            )
                        }
                    }
                }
            }
        }

        // Logout confirmation dialog
        if (showLogoutDialog) {
            AlertDialog(
                // Dialog properties
                onDismissRequest = { showLogoutDialog = false },
                // Dialog content
                title = {
                    Text(
                        "Confirm Logout",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                text = {
                    Text(
                        "Are you sure you want to log out?",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold) },
                // Log out button
                confirmButton = {
                    TextButton(
                        // Log out button action
                        onClick = {
                        AuthHelper.logOut()
                        showLogoutDialog = false
                        navController.navigate("login") {
                            popUpTo("login") { inclusive = true }
                        }
                    }) {
                        // Log out button text
                        Text(
                            "Log Out",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.error)
                    }
                },
                // Cancel button
                dismissButton = {
                    TextButton(
                        onClick = { showLogoutDialog = false })
                    {
                        Text(
                            "Cancel",
                            fontSize = 20.sp,)
                    }
                }
            )
        }
    }
}
