package com.felipe.starwars.features.category

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.felipe.starwars.R
import com.felipe.starwars.databinding.ActivityCategoriesBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewpager.adapter = CategoriesPageAdapter(this)

        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.categories_title)
                else -> getString(R.string.categories_favorites_title)
            }
        }.attach()
    }
}
