package com.example.core.bases.adapter.helpers

class FilterManager<T>(
    private val strategy: FilterStrategy<T>
) {
    fun filter(items: List<T>, query: String): List<T> {
        return if (query.isBlank()) {
            items
        } else {
            strategy.filter(items, query)
        }
    }
}

