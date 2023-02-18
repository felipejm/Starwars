package com.felipe.starwars.features.category.detail.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felipe.starwars.databinding.ItemCardCategoryDetailBinding
import com.felipe.starwars.features.category.detail.domain.CategoryDetail
import com.squareup.picasso.Picasso

class CategoryDetailAdapter : RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder>() {

    var categoryDetails: List<CategoryDetail> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCardCategoryDetailBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryDetails[position])
    }

    override fun getItemCount() = categoryDetails.size

    inner class ViewHolder(val binding: ItemCardCategoryDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(categoryDetail: CategoryDetail) {
            Picasso.get().isLoggingEnabled = true
            val context = binding.root.context
            Picasso.get().load(categoryDetail.imageUrl).into(binding.imageView)
            binding.titleTextView.text = categoryDetail.title

            binding.firstAttribute.text =
                context.getString(categoryDetail.firstAttributeLabel) + categoryDetail.firstAttribute

            binding.secondAttribute.text =
                context.getString(categoryDetail.secondAttributeLabel) + categoryDetail.secondAttribute

            binding.thirdAttribute.text =
                context.getString(categoryDetail.thirdAttributeLabel) + categoryDetail.thirdAttribute

            binding.fourthAttribute.text =
                context.getString(categoryDetail.fourthAttributeLabel) + categoryDetail.fourthAttribute
        }
    }
}
