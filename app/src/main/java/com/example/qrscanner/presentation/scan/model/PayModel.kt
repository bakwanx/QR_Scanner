package com.example.qrscanner.presentation.scan.model

import java.util.Calendar
import java.util.Date

class PayModel {
    var bank: String = ""
    var idTransaction: String = ""
    var merchant: String = ""
    var nominal: Int = 0
    var date: Date = Calendar.getInstance().time

    constructor(bank: String, idTransaction: String, merchant: String, nominal: Int, date: Date) {
        this.bank = bank
        this.idTransaction = idTransaction
        this.merchant = merchant
        this.nominal = nominal
        this.date = date
    }
}