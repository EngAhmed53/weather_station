package com.shouman.apps.weatherstation.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.*
import com.shouman.apps.weatherstation.api.LocationApiServices
import com.shouman.apps.weatherstation.data.model.toPreferenceLocation
import com.shouman.apps.weatherstation.repository.MainRepository
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class LocationViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val _permissionState = MutableLiveData<Boolean>()
    val permissionState: LiveData<Boolean>
        get() = _permissionState

    private val mainRepository: MainRepository by inject(MainRepository::class.java)

    val locationLiveData = mainRepository.locationLiveData

    private val locationApiServices:LocationApiServices by inject(LocationApiServices::class.java)

    private val apiKey:String by inject(String::class.java)

    private val fusedLocation: FusedLocationProviderClient? =
        LocationServices.getFusedLocationProviderClient(application)

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            result?.let {
                val location = it.lastLocation
                val q = "${location.latitude}, ${location.longitude}"
                viewModelScope.launch {
                    val preferenceLocation = locationApiServices.getCurrentLocationAsync(apiKey, q).await()?.toPreferenceLocation()
                    println("location $preferenceLocation")
                    mainRepository.updateLocation(preferenceLocation)
                }
            }
        }
    }

    fun requestLocationUpdate() {
        _permissionState.value = !(ActivityCompat.checkSelfPermission(
            getApplication(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            getApplication(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED)
    }

    @SuppressLint("MissingPermission")
    fun updateLocation() {

        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        fusedLocation?.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            Looper.getMainLooper()
        )
    }

    fun restorePermissionState() {
        _permissionState.value = null
    }

    override fun onCleared() {
        super.onCleared()
        fusedLocation?.removeLocationUpdates(mLocationCallback)
    }
}