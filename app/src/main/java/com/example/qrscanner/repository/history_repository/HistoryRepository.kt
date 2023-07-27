package com.example.qrscanner.repository.history_repository

import com.example.qrscanner.presentation.pay.model.PayModel

interface HistoryRepository {
    fun addHistory(payModel: PayModel)

    fun getHistory(): List<PayModel>
}