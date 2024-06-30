package com.example.traveltaipei.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AttractionResponse(
    val total: Int,
    val data: List<Attraction>
) : Parcelable

@Parcelize
data class Attraction(
    val id: Int,
    val name: String,
    val name_zh: String?,
    val open_status: Int,
    val introduction: String,
    val open_time: String,
    val zipcode: String,
    val distric: String,
    val address: String,
    val tel: String,
    val fax: String?,
    val email: String?,
    val months: String,
    val nlat: String,
    val elong: String,
    val official_site: String?,
    val facebook: String?,
    val ticket: String?,
    val remind: String,
    val staytime: String?,
    val modified: String,
    val url: String,
    val category: List<Category>,
    val target: List<Target>,
    val service: List<Service>,
    val friendly: List<Friendly> = emptyList(),
    val images: List<Image>,
    val files: List<File> = emptyList(),
    val links: List<Link> = emptyList()
) : Parcelable

@Parcelize
data class Category(
    val id: Int,
    val name: String
) : Parcelable

@Parcelize
data class Target(
    val id: Int,
    val name: String
) : Parcelable

@Parcelize
data class Service(
    val id: Int,
    val name: String
) : Parcelable

@Parcelize
data class Friendly(
    val id: Int,
    val name: String
) : Parcelable

@Parcelize
data class Image(
    val src: String,
    val subject: String,
    val ext: String
) : Parcelable

@Parcelize
data class File(
    val src: String,
    val subject: String,
    val ext: String
) : Parcelable

@Parcelize
data class Link(
    val href: String,
    val title: String
) : Parcelable
