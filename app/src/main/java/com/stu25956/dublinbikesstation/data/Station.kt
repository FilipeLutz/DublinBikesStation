package com.stu25956.dublinbikesstation.data

// Data class representing a Dublin Bikes station.
data class Station(
    val number: Int,
    val name: String,
    val address: String,
    val position: Position,
    val available_bikes: Int,
    val available_bike_stands: Int,
    val status: String
)
// Data class representing a position on the map.
data class Position(
    val lat: Double,
    val lng: Double
)