package com.example.qrscanner.domain

import com.example.qrscanner.presentation.home.model.PromoModel
import com.example.qrscanner.presentation.pay.model.PayModel
import com.example.qrscanner.repository.promo_repository.PromoRepository
import javax.inject.Inject

class PromoUseCase @Inject constructor(
    private val promoRepository: PromoRepository
) {
    suspend fun getPromos(): List<PromoModel> {
        try{
            val response = promoRepository.getPromoList()
            if (response.isSuccessful) {
                return response.body()!!
            }

            throw Exception("Can't get response ")
        }catch (e: Exception){
            throw e
        }
    }
}