package com.example.qrscanner.repository.promo_repository

import androidx.camera.core.ImageProcessor.Response
import com.example.qrscanner.presentation.home.model.PromoModel

interface PromoRepository {

    suspend fun getPromoList(): retrofit2.Response<List<PromoModel>>

}