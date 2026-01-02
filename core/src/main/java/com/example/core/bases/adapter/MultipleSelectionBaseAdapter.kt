package com.example.core.bases.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class MultipleSelectionBaseAdapter<T, VH : BaseViewHolder<T>>(
    private val onSelectionChanged: (Set<Long>) -> Unit,
    private val getItemId: (T) -> Long
) : RecyclerView.Adapter<VH>() {

    private val selectedItems = mutableSetOf<Long>()

    fun getSelectedItems(): Set<Long> = selectedItems.toSet()

    fun toggleSelection(itemId: Long) {
        if (selectedItems.contains(itemId)) {
            selectedItems.remove(itemId)
        } else {
            selectedItems.add(itemId)
        }
        onSelectionChanged(selectedItems.toSet())
        notifyDataSetChanged()
    }

    fun clearSelection() {
        selectedItems.clear()
        onSelectionChanged(emptySet())
        notifyDataSetChanged()
    }

    fun isSelected(itemId: Long): Boolean = selectedItems.contains(itemId)
}

