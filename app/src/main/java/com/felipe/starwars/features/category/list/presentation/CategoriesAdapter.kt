package com.felipe.starwars.features.category.list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felipe.starwars.databinding.ItemCardCategoryBinding
import com.felipe.starwars.features.category.list.domain.Category
import com.squareup.picasso.Picasso

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    var onItemClickListener: (Category) -> Unit = {}
    var onFavoriteClickListener: (Boolean, Category) -> Unit = { _, _ -> }

    var categories: List<Category> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCardCategoryBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount() = categories.size

    inner class ViewHolder(val binding: ItemCardCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.titleTextView.text = category.title
            binding.container.setOnClickListener { onItemClickListener(category) }

            binding.checkBoxFavorite.setOnCheckedChangeListener(null)
            binding.checkBoxFavorite.isChecked = category.isFavorite

            binding.checkBoxFavorite.setOnCheckedChangeListener { _, checked ->
                onFavoriteClickListener(checked, category)
            }

            Picasso.get().load(category.imageUrl).into(binding.imageView)
        }
    }
}
