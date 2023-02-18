package com.felipe.starwars.features.category.detail.presentation

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.felipe.starwars.R
import com.felipe.starwars.base.presentation.ViewState
import com.felipe.starwars.databinding.ActivityCategoryDetailBinding
import com.felipe.starwars.features.category.detail.domain.CategoryDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryDetailBinding

    private val viewModel by viewModels<CategoryDetailViewModel>()
    private val categoryDetailAdapter by lazy { CategoryDetailAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycle.addObserver(viewModel)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        watchObservers()
        setupRecyclerView()
    }

    private fun watchObservers() {
        viewModel.categoryDetailsLiveData.observe(this) {
            when (it) {
                is ViewState.Error -> setupError()
                is ViewState.Loading -> setupLoading()
                is ViewState.Success -> setupView(it)
                is ViewState.Empty -> setupEmptyView()
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            val layoutManagerGrid =
                if (resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    GridLayoutManager(this@CategoriesDetailActivity, 1)
                } else {
                    GridLayoutManager(this@CategoriesDetailActivity, 2)
                }

            layoutManager = layoutManagerGrid
            adapter = categoryDetailAdapter
        }
    }

    private fun setupView(it: ViewState.Success<List<CategoryDetail>>) {
        categoryDetailAdapter.categoryDetails = it.value
        binding.viewError.root.isVisible = false
        binding.recyclerView.isVisible = true
        binding.progressBar.isVisible = false
        binding.viewEmpty.root.isVisible = false
    }

    private fun setupLoading() {
        binding.viewError.root.isVisible = false
        binding.recyclerView.isVisible = false
        binding.progressBar.isVisible = true
        binding.viewEmpty.root.isVisible = false
    }

    private fun setupEmptyView() {
        binding.viewError.root.isVisible = false
        binding.recyclerView.isVisible = false
        binding.progressBar.isVisible = false
        binding.viewEmpty.root.isVisible = true
    }

    private fun setupError() {
        binding.viewError.root.isVisible = true
        binding.recyclerView.isVisible = false
        binding.progressBar.isVisible = false
        binding.viewEmpty.root.isVisible = false
        binding.viewError.root.setOnClickListener { viewModel.tryAgain() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {

        const val CATEGORY_ARGS = "CATEGORY_ARGS"

        fun intent(context: Context, category: String) =
            Intent(context, CategoriesDetailActivity::class.java).apply {
                putExtra(CATEGORY_ARGS, category)
            }
    }
}
