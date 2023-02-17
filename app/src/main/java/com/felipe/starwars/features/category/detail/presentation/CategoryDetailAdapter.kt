package com.felipe.starwars.features.category.detail.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felipe.starwars.databinding.ItemCardCategoryBinding
import com.felipe.starwars.features.category.detail.domain.CategoryDetail
import com.squareup.picasso.Picasso

class CategoryDetailAdapter : RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder>() {

    var categoryDetails: List<CategoryDetail> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCardCategoryBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryDetails[position])
    }

    override fun getItemCount() = categoryDetails.size

    inner class ViewHolder(val binding: ItemCardCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(categoryDetail: CategoryDetail) {
            val context = binding.root.context
            binding.titleTextView.text = categoryDetail.title
            Picasso.get().load(categoryDetail.imageUrl).into(binding.imageView)
        }
    }
}
