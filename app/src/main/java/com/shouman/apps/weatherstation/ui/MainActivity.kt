package com.shouman.apps.weatherstation.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.shouman.apps.weatherstation.R
import com.shouman.apps.weatherstation.adapters.FragmentAdapter
import com.shouman.apps.weatherstation.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel


private const val MY_PERMISSIONS_ACCESS_FINE_LOCATION: Int = 555
private const val LOCATION_REQUEST = 225

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val locationViewModel: LocationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        locationViewModel.locationLiveData.observe(this, { preferenceLocation ->

            if (preferenceLocation == null) {
                locationViewModel.requestLocationUpdate()
            } else {
                mBinding.pager.adapter = FragmentAdapter(this)
            }
        })

        locationViewModel.permissionState.observe(this, {
            if (it == false) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    MY_PERMISSIONS_ACCESS_FINE_LOCATION
                )
            } else if (it == true) {
                checkLocationProviders()
            }
        })
    }

    private fun checkLocationProviders() {
        if (!isLocationEnabled()) {
            Toast.makeText(this, R.string.turn_on_location, Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            if (intent.resolveActivity(packageManager) != null) startActivityForResult(
                intent,
                LOCATION_REQUEST
            )
        } else {
            locationViewModel.updateLocation()
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager =
            this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == MY_PERMISSIONS_ACCESS_FINE_LOCATION) {

            // If request is cancelled, the result arrays are empty.
            if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                checkLocationProviders()
                locationViewModel.restorePermissionState()
            } else {
                Toast.makeText(
                    this,
                    R.string.location_permission_needed,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}