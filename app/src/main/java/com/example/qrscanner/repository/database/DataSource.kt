package com.example.qrscanner.repository.database

import androidx.compose.runtime.mutableStateOf

class DataSource {

    private var db : Int = 0

    fun setBalance(balance: Int) = db + balance

    fun getBalance(): Int = db

}