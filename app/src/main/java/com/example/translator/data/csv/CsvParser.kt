package com.example.translator.data.csv

interface CsvParser<T> {
    fun parse(csvLine: String): T?
    fun parseList(csvLines: List<String>): List<T>
}

