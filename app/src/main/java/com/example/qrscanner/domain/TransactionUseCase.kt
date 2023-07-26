package com.example.qrscanner.domain

import com.example.qrscanner.repository.balance_repository.BalanceRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

class TransactionUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository
) {

    fun doTransaction(bill: Int): Boolean {
        if(bill == 0) throw IllegalArgumentException("Tagihan tidak boleh 0")
        if(balanceRepository.getBalance() < bill) return false
        val totalBalance = balanceRepository.getBalance() - bill
        balanceRepository.setBalance(totalBalance)

        return true
    }



    fun getBalance(): Int {
        return balanceRepository.getBalance()
    }
}