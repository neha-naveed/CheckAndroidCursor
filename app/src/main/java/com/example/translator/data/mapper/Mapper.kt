package com.example.translator.data.mapper

interface Mapper<From, To> {
    fun map(from: From): To
}

