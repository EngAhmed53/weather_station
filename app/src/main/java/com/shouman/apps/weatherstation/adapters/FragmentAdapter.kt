package com.shouman.apps.weatherstation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.shouman.apps.weatherstation.ui.detailsFragment.DetailsFragment
import com.shouman.apps.weatherstation.ui.mainFragment.MainFragment

private const val ITEM_COUNT = 2

class FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment.newInstance()
            1 -> DetailsFragment.newInstance()
            else -> throw IllegalArgumentException("no such fragment")
        }
    }


}