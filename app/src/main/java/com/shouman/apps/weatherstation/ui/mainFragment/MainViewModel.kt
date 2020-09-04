package com.shouman.apps.weatherstation.ui.mainFragment

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shouman.apps.weatherstation.api.NetworkCall
import com.shouman.apps.weatherstation.repository.MainRepository
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MainViewModel : ViewModel() {
    private val mainRepository :MainRepository by inject(MainRepository::class.java)

    val locationLiveData = mainRepository.locationLiveData

    val currentConditionLiveData = mainRepository.currentConditionLiveData

    val todayConditionLiveData = Transformations.map(mainRepository.next5DaysData){
        it.firstOrNull()
    }
}