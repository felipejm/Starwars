package com.felipe.starwars.features.category.list.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.felipe.starwars.base.presentation.ViewState
import com.felipe.starwars.databinding.FragmentCategoriesBinding
import com.felipe.starwars.features.category.detail.presentation.CategoriesDetailActivity
import com.felipe.starwars.features.category.list.CategoriesFilter
import com.felipe.starwars.features.category.list.domain.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CategoriesViewModel>()
    private val categoriesAdapter by lazy { CategoriesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@CategoriesFragment
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)
        setupRecyclerView()
        setupClickListeners()
        watchObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun watchObservers() {
        viewModel.categoriesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Error -> setupError()
                is ViewState.Loading -> setupLoading()
                is ViewState.Success -> setupView(it)
                is ViewState.Empty -> setupEmptyView()
            }
        }

        viewModel.commandLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is CategoriesCommand.OpenCategory -> {
                    startActivity(CategoriesDetailActivity.intent(requireContext(), it.category))
                }
            }
        }
    }

    private fun setupClickListeners() {
        categoriesAdapter.onItemClickListener = {
            viewModel.onCategoryClicked(it)
        }

        categoriesAdapter.onFavoriteClickListener = { checked, category ->
            viewModel.onFavoriteClicked(checked, category)
        }
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            val layoutManagerGrid =
                if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    GridLayoutManager(activity, 2)
                } else {
                    GridLayoutManager(activity, 3)
                }

            layoutManager = layoutManagerGrid
            adapter = categoriesAdapter
        }
    }

    private fun setupView(it: ViewState.Success<List<Category>>) {
        categoriesAdapter.categories = it.value
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

    companion object {

        const val FILTER_ARGS = "FILTER_ARGS"

        fun newInstance(filter: CategoriesFilter = CategoriesFilter.REMOTE) =
            CategoriesFragment().apply {
                arguments = bundleOf(FILTER_ARGS to filter)
            }
    }
}
