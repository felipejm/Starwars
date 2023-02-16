package com.felipe.starwars.features.category

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.felipe.starwars.features.category.list.CategoriesFilter
import com.felipe.starwars.features.category.list.presentation.CategoriesFragment

class CategoriesPageAdapter(
    activity: FragmentActivity,
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CategoriesFragment.newInstance()
            else -> CategoriesFragment.newInstance(CategoriesFilter.LOCAL)
        }
    }
}
