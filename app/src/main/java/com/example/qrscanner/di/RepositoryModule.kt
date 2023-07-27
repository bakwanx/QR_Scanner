package com.example.qrscanner.di

import com.example.qrscanner.repository.balance_repository.BalanceRepository
import com.example.qrscanner.repository.balance_repository.BalanceRepositoryImpl
import com.example.qrscanner.repository.database.BalanceDataSource
import com.example.qrscanner.repository.database.HistoryDataSource
import com.example.qrscanner.repository.history_repository.HistoryRepository
import com.example.qrscanner.repository.history_repository.HistoryRepositoryImpl
import com.example.qrscanner.repository.promo_repository.PromoRepository
import com.example.qrscanner.repository.promo_repository.PromoRepositoryImpl
import com.example.qrscanner.rest_api.ApiClient
import com.example.qrscanner.rest_api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesApiService(): ApiService = ApiClient.getService()

    @Provides
    fun providesPromoRepositoryApi(
        apiService: ApiService
    ): PromoRepositoryImpl = PromoRepositoryImpl(apiService)

    @Provides
    fun providesPromoRepository(
        promoRepositoryImpl: PromoRepositoryImpl
    ): PromoRepository = promoRepositoryImpl

    @Provides
    @Singleton
    fun providesBalanceDataSource(): BalanceDataSource = BalanceDataSource()

    @Provides
    @Singleton
    fun providesHistoryDataSource(): HistoryDataSource = HistoryDataSource()

    @Provides
    @Singleton
    fun providesBalanceRepository (
        balanceRepositoryImpl: BalanceRepositoryImpl
    ): BalanceRepository = balanceRepositoryImpl

    @Provides
    @Singleton
    fun providesHistoryRepository (
        historyRepositoryImpl: HistoryRepositoryImpl
    ): HistoryRepository = historyRepositoryImpl

}

