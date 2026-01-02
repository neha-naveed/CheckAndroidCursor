package com.example.translator.data.csv

class CsvParserImpl<T> : CsvParser<T> {
    override fun parse(csvLine: String): T? {
        // Implementation for CSV parsing
        return null
    }

    override fun parseList(csvLines: List<String>): List<T> {
        return csvLines.mapNotNull { parse(it) }
    }
}

