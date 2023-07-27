package com.example.qrscanner.rest_api

import androidx.camera.core.ImageProcessor.Response
import com.example.qrscanner.presentation.home.model.PromoModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiService {
    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("promos")
    suspend fun getPromoList(
//        @Header("Authorization") token: String
    ): retrofit2.Response<List<PromoModel>>
}