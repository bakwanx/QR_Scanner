package com.example.qrscanner.presentation.home.model


class PromoModel(
    val id: Long,


    val title: String,


    val publishedAt: String,


    val updatedAt: String,


    val namePromo: String,


    val descPromo: String,

    val nama: String,
    val desc: String,
    val latitude: String? = null,
    val longitude: String? = null,
    val alt: String? = null,
    val createdAt: String,
    val lokasi: String,
    val count: Long,
    val img: Img
) : java.io.Serializable


class Img(
    val id: Long,
    val name: String,
    val alternativeText: String,
    val caption: String,
    val width: Long,
    val height: Long,
    val formats: Formats,
    val hash: String,
    val ext: String,
    val mime: String,
    val size: Double,
    val url: String,


    val previewURL: String,

    val provider: String,


    val providerMetadata: String,

    val createdAt: String,


    val updatedAt: String
) : java.io.Serializable


class Formats(
    val small: ImageSize,
    val medium: ImageSize,
    val thumbnail: ImageSize,
    val imageSize: ImageSize? = null
) : java.io.Serializable


class ImageSize(
    val ext: String,
    val url: String,
    val hash: String,
    val mime: String,
    val name: String,
    val path: String,
    val size: Double,
    val width: Long,
    val height: Long
) : java.io.Serializable