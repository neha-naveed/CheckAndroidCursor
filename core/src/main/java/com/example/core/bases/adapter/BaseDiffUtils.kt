package com.example.core.bases.adapter

import androidx.recyclerview.widget.DiffUtil

object BaseDiffUtils {
    fun <T> getDefaultCallback(): DiffUtil.ItemCallback<T> {
        return object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                return oldItem == newItem
            }
        }
    }
}

