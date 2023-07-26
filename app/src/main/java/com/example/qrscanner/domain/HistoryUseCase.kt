package com.example.qrscanner.domain

import com.example.qrscanner.presentation.scan.model.PayModel
import com.example.qrscanner.repository.history_repository.HistoryRepository
import javax.inject.Inject

class HistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {

    fun addHistory(payModel: PayModel) {
        historyRepository.addHistory(payModel)
    }

    fun getHistory(): List<PayModel> {
        return historyRepository.getHistory()
    }
}