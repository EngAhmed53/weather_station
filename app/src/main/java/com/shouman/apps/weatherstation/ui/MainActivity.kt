package com.shouman.apps.weatherstation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.shouman.apps.weatherstation.R
import com.shouman.apps.weatherstation.adapters.FragmentAdapter
import com.shouman.apps.weatherstation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mBinding.pager.adapter = FragmentAdapter(this)

    }
}