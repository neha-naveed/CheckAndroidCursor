package com.example.core.bases.adapter.helpers

interface FilterStrategy<T> {
    fun filter(items: List<T>, query: String): List<T>
}

