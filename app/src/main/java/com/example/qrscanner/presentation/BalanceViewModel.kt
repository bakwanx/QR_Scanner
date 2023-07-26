package com.example.qrscanner.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrscanner.domain.BalanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(
    private val balanceUseCase: BalanceUseCase
) : ViewModel() {
    private val _viewState: MutableState<Int?> = mutableStateOf(null)
    val viewState: State<Int?> = _viewState
    private val _getBalance = MutableLiveData<Int>()
    val getBalance: LiveData<Int> = _getBalance

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getBalance()
            }
        }
    }


    fun getBalance() {
        _getBalance.postValue(balanceUseCase.getBalance())
    }

    fun doTransaction(bill: Int) : Boolean{
        return balanceUseCase.doTransaction(bill)
    }

}