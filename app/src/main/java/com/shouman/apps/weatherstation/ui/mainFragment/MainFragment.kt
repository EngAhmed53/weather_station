package com.shouman.apps.weatherstation.ui.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shouman.apps.weatherstation.databinding.MainFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mMainViewModel: MainViewModel by viewModel()
//    private val locationViewModel: LocationViewModel by viewModel()

    private lateinit var mBinding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = MainFragmentBinding.inflate(inflater)

        mBinding.apply {
            lifecycleOwner = this@MainFragment
            mainViewModel = mMainViewModel
        }

        return mBinding.root
    }

}