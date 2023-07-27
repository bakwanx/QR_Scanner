package com.example.qrscanner.presentation.home.model

import com.google.gson.JsonElement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PromoModel (
    val id: Long,

    @SerialName("Title")
    val title: JsonElement? = null,

    @SerialName("published_at")
    val publishedAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    @SerialName("name_promo")
    val namePromo: JsonElement? = null,

    @SerialName("desc_promo")
    val descPromo: JsonElement? = null,

    val nama: String,
    val desc: String,
    val latitude: String? = null,
    val longitude: String? = null,
    val alt: String? = null,
    val createdAt: String,
    val lokasi: String,
    val count: Long,
    val img: Img
)

@Serializable
data class Img (
    val id: Long,
    val name: String,
    val alternativeText: String,
    val caption: String,
    val width: Long,
    val height: Long,
    val formats: Formats,
    val hash: String,
    val ext: StringBuffer,
    val mime: String,
    val size: Double,
    val url: String,

    @SerialName("previewUrl")
    val previewURL: JsonElement? = null,

    val provider: String,

    @SerialName("provider_metadata")
    val providerMetadata: JsonElement? = null,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String
)

@Serializable
data class Formats (
    val small: Large,
    val medium: Large,
    val thumbnail: Large,
    val large: Large? = null
)

@Serializable
data class Large (
    val ext: String,
    val url: String,
    val hash: String,
    val mime: String,
    val name: String,
    val path: JsonElement? = null,
    val size: Double,
    val width: Long,
    val height: Long
)