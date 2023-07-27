package com.example.qrscanner.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrscanner.domain.HistoryUseCase
import com.example.qrscanner.domain.TransactionUseCase
import com.example.qrscanner.presentation.pay.model.PayModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionUseCase: TransactionUseCase,
    private val historyUseCase: HistoryUseCase
) : ViewModel() {
    private val _getBalance = MutableLiveData<Int>()
    private val _getHistoryModels = MutableLiveData<List<PayModel>>()
    val getBalance: LiveData<Int> = _getBalance
    val getHistoryModels: LiveData<List<PayModel>> = _getHistoryModels

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getBalance()
            }
        }

    }

    fun getBalance() {
        _getBalance.postValue(transactionUseCase.getBalance())

    }

    suspend fun suspend(){
        currentCoroutineContext()
    }

    fun doTransaction(bill: Int) : Boolean{
        return transactionUseCase.doTransaction(bill)
    }

    fun addHistory(payModel: PayModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                historyUseCase.addHistory(payModel)
            }
        }
    }

    fun getHistory() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _getHistoryModels.postValue(historyUseCase.getHistory())
            }
        }
    }

}