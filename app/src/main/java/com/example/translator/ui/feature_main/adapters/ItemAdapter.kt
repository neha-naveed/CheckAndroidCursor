package com.example.translator.ui.feature_main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.translator.core.bases.adapter.BaseAdapter
import com.example.translator.core.bases.adapter.BaseViewHolder
import com.example.translator.databinding.ItemListBinding
import com.example.translator.domain.model.TranslationHistory

class ItemAdapter(
    private val onItemClick: (TranslationHistory) -> Unit,
    private val onItemDelete: (Long) -> Unit
) : BaseAdapter<TranslationHistory, ItemAdapter.ItemViewHolder>() {

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
                root.setOnClickListener { onItemClick(item) }
            }
        }
    }
}

