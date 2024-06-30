package com.example.traveltaipei.ultils

import android.location.Location

object AttractionUtils {
    fun calculateDistance(itemLat: String, itemLong: String): Double {
        val itemLocation = Location("itemLocation").apply {
            latitude = itemLat.toDoubleOrNull() ?: 0.0
            longitude = itemLong.toDoubleOrNull() ?: 0.0
        }

        val userLocation = Location("userLocation").apply {
            // fake user location
            latitude = 25.033851
            longitude = 121.594507
        }

        return itemLocation.distanceTo(userLocation).toDouble()
    }
}