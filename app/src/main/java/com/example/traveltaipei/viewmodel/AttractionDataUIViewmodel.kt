package com.example.traveltaipei.viewmodel

import android.os.Parcelable
import com.example.traveltaipei.data.model.Attraction
import com.example.traveltaipei.data.model.Image
import com.example.traveltaipei.ultils.AttractionUtils
import kotlinx.parcelize.Parcelize

@Parcelize
data class AttractionDataUIViewmodel(
    val id: String = "",
    val name: String = "",
    val introduction: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val modified: String = "",
    val url: String = "",
    val address: String = "",
    val image: List<Image> = emptyList(),
    val distance: Double = 0.0,
    val isVisibility: Boolean = false,
) : Parcelable {
    companion object {
        fun mapToUI(response: Attraction): AttractionDataUIViewmodel {
            return AttractionDataUIViewmodel(
                id = response.id.toString(),
                name = response.name,
                introduction = response.introduction,
                latitude = response.nlat.toDoubleOrNull() ?: 0.0,
                longitude = response.elong.toDoubleOrNull() ?: 0.0,
                modified = response.modified,
                url = response.url,
                address = response.address,
                image = response.images,
                distance = AttractionUtils.calculateDistance(itemLat = response.nlat, itemLong = response.elong),
                isVisibility = response.images.isNullOrEmpty().not(),
            )
        }
    }
}