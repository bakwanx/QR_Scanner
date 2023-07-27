package com.example.qrscanner.repository.promo_repository

import com.example.qrscanner.presentation.home.model.PromoModel
import com.example.qrscanner.rest_api.ApiService
import com.example.qrscanner.util.JwtToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PromoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): PromoRepository {

    override suspend fun getPromoList(): Response<List<PromoModel>> {
        return apiService.getPromoList()
    }

}