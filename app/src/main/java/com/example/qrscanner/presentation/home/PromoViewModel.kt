package com.example.qrscanner.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrscanner.domain.PromoUseCase
import com.example.qrscanner.presentation.home.model.PromoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PromoViewModel @Inject constructor(
    private val promoUseCase: PromoUseCase
) : ViewModel() {

    private val _getPromoModels = MutableLiveData<List<PromoModel>>()
    val getPromoModels: LiveData<List<PromoModel>> = _getPromoModels

    init {
        getPromos()
    }

    fun getPromos(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    _getPromoModels.postValue(promoUseCase.getPromos())
                }catch (e: Exception){
                    Log.d("TAG", "getPromos: $e")
                }

            }
        }
    }
}