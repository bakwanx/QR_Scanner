package com.example.qrscanner.util

import com.example.qrscanner.presentation.pay.model.PayModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun scanText(text: String) : PayModel {
    val splitArr = text.split('.').toTypedArray()

    return PayModel(
        bank = splitArr[0],
        idTransaction =  splitArr[1],
        merchant = splitArr[2],
        nominal = splitArr[3].toInt(),
        date = Calendar.getInstance().time
    )
}

fun formatDate(date: Date): String{
    val pattern = "dd-MM-yyyy"
    val simpleDateFormat = SimpleDateFormat(pattern)
    val date: String = simpleDateFormat.format(Date())

    return date
}

fun formateTime(date: Date): String {
    val pattern = "HH:mm:ss"
    val simpleDateFormat = SimpleDateFormat(pattern)
    val date: String = simpleDateFormat.format(Date())

    return date
}

fun String.toCurrencyFormat(): String {
    val localeID = Locale("in", "ID")
    val doubleValue = this.toDoubleOrNull() ?: return this
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.minimumFractionDigits = 0
    return numberFormat.format(doubleValue)
}