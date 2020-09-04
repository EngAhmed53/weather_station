package com.shouman.apps.weatherstation.ui.mainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shouman.apps.weatherstation.api.NetworkCall
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun init() {
        viewModelScope.launch {

            val next5DaysApiServices = NetworkCall.next5DaysApiServices
            val list = next5DaysApiServices.getNext5DaysConditionAsync(126907, "0OcSbcnezllUOluGq7fiZsoqzCT8hgTy", "en-us", details = false, true).await()
            println(list)
        }
    }

}