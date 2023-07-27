package com.example.qrscanner.promo_usecase_test

import com.example.qrscanner.domain.PromoUseCase
import com.example.qrscanner.domain.TransactionUseCase
import com.example.qrscanner.presentation.home.model.Formats
import com.example.qrscanner.presentation.home.model.ImageSize
import com.example.qrscanner.presentation.home.model.Img
import com.example.qrscanner.presentation.home.model.PromoModel
import com.example.qrscanner.repository.balance_repository.BalanceRepository
import com.example.qrscanner.repository.promo_repository.PromoRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PromoUseCaseTest {
    private var promoRepository = mockk<PromoRepository>()
    private lateinit var promoUseCase: PromoUseCase
    private var dataTest: DataPromoUsecaseTest = DataPromoUsecaseTest()

    @Before
    fun setUp() {
        promoUseCase = PromoUseCase(promoRepository)
    }

    @Test
    fun testSuccessGetPromo() {
        coEvery {
            promoRepository.getPromoList().body()
        } returns dataTest.dataTest()
        GlobalScope.launch {
            val result = promoUseCase.getPromos()
            Assert.assertEquals( dataTest.dataTest(), result.size)
        }

    }

}