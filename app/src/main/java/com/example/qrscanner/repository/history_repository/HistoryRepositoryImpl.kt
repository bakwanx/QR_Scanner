package com.example.qrscanner.repository.history_repository

import com.example.qrscanner.presentation.pay.model.PayModel
import com.example.qrscanner.repository.database.HistoryDataSource
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyDataSource: HistoryDataSource
) : HistoryRepository {

    override fun addHistory(payModel: PayModel) {
        historyDataSource.add(payModel)
    }

    override fun getHistory(): List<PayModel> {
        return historyDataSource.get()
    }
}