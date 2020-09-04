package com.shouman.apps.weatherstation.databindnig

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.shouman.apps.weatherstation.data.model.PreferenceLocation

@BindingAdapter("location")
fun TextView.setText(location: PreferenceLocation?) {
    location?.let {
        text = "${it.city}, ${it.country}"
    }
}


