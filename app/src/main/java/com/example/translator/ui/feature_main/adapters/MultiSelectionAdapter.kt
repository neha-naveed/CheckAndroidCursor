package com.example.translator.ui.feature_main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.translator.core.bases.adapter.BaseViewHolder
import com.example.translator.core.bases.adapter.MultipleSelectionBaseAdapter
import com.example.translator.databinding.ItemListBinding
import com.example.translator.domain.model.TranslationHistory

class MultiSelectionAdapter(
    onSelectionChanged: (Set<Long>) -> Unit
) : MultipleSelectionBaseAdapter<TranslationHistory, MultiSelectionAdapter.ItemViewHolder>(
    onSelectionChanged = onSelectionChanged,
    getItemId = { it.id }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    inner class ItemViewHolder(
        private val binding: ItemListBinding
    ) : BaseViewHolder<TranslationHistory>(binding.root) {
        override fun bind(item: TranslationHistory) {
            binding.apply {
                // Bind data to views
            }
        }
    }
}

