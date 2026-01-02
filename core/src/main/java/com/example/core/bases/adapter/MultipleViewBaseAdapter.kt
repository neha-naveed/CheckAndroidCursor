package com.example.core.bases.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class MultipleViewBaseAdapter<T, VH : BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {
    protected val items = mutableListOf<T>()

    fun submitList(newList: List<T>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size
}

