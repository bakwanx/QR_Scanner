package com.example.qrscanner.repository.database

import com.example.qrscanner.presentation.scan.model.PayModel

class HistoryDataSource {
    private val db = mutableListOf<PayModel>()

    fun add(payModel: PayModel) = db.add(payModel)

    fun get(): List<PayModel> = db
}