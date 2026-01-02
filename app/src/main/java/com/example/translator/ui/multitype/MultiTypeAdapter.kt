package com.example.translator.ui.multitype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.translator.core.bases.adapter.BaseViewHolder

class MultiTypeAdapter(
    private val items: List<MultiTypeModel>
) : RecyclerView.Adapter<BaseViewHolder<MultiTypeModel>>() {

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MultiTypeModel> {
        // Create appropriate view holder based on type
        return when (viewType) {
            // Define view types
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<MultiTypeModel>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

