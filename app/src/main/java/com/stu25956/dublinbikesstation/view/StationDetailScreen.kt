package com.stu25956.dublinbikesstation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.stu25956.dublinbikesstation.data.StationRepository

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
// StationDetailScreen composable function
@Composable
// StationDetailScreen function with stationId and navController parameters
fun StationDetailScreen(stationId: Int, navController: NavController) {
    // Load station from StationRepository
    val station = StationRepository
        .loadStations(LocalContext.current)
        .find { it.number == stationId }
    // If station is not null
    if (station != null)
    {
        // Camera Position State
        val cameraPositionState = rememberCameraPositionState{
            position = CameraPosition.fromLatLngZoom(
                LatLng(
                    station.position.lat,
                    station.position.lng),
                15f
            )
        }
        // Scaffold with TopAppBar and Column
        Scaffold(
            topBar = {
                // TopAppBar with title and navigationIcon
                TopAppBar(
                    title = {
                        Box (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 45.dp)
                        ){
                            // Station Detail Text as Title
                            Text(
                                "Station Details",
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    // BackArrow navigationIcon button
                    navigationIcon = {
                        IconButton(
                            // onClick popBackStack
                            onClick = { navController.popBackStack()
                            }
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back to Station List",
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(start = 10.dp)
                            )
                        }
                    }
                )
            },

            content = {
                // Column with Station Info Container and Google Maps Container
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {

                    // Station Info Container
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(20.dp)
                            .shadow(10.dp, RoundedCornerShape(8.dp)),
                    ) {
                        // Box to center content in the Card
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            // Column for the station info
                            Column(
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                            ) {
                                // Fixed-size Box for the station name
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                ) {
                                    // Station Name
                                    Text(
                                        station.name,
                                        style = MaterialTheme.typography.headlineMedium,
                                        textAlign = TextAlign.Center,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }

                                Spacer(modifier = Modifier.height(15.dp))

                                // Address
                                Text(
                                    "Address: ${station.address}",
                                    fontSize = 18.sp,
                                )

                                Spacer(modifier = Modifier.height(15.dp))

                                // Available Bikes
                                Text(
                                    "Available Bikes: ${station.available_bikes}",
                                    fontSize = 20.sp,
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                // Available Stands
                                Text(
                                    "Available Stands: ${station.available_bike_stands}",
                                    fontSize = 20.sp,
                                )

                                Spacer(modifier = Modifier.height(15.dp))

                                // Status of the station
                                Text(
                                    "Status: ${station.status}",
                                    fontSize = 20.sp,
                                )
                            }
                        }
                    }

                    // Google Maps Container
                    Card(
                        modifier = Modifier
                            .height(350.dp)
                            .padding(16.dp)
                            .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
                            .shadow(10.dp, RoundedCornerShape(8.dp)
                        ),
                    ) {
                        // GoogleMap with Marker for the station
                        GoogleMap(
                            modifier = Modifier.fillMaxSize(),
                            cameraPositionState = cameraPositionState
                        ) {
                            // Marker for the station
                            Marker(
                                state = MarkerState(
                                    position = LatLng(
                                        station.position.lat,
                                        station.position.lng
                                    )
                                ),
                                // Marker title and snippet
                                title = station.name,
                                snippet = station.address
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    // Button to navigate back to StationList
                    Button(
                        onClick = {
                            navController.navigate(
                                "stationList"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            "Back to Station List",
                            fontSize = 20.sp)
                    }
                }
            }
        )
    }
}
