package com.classic.countrynamefetch.apiFetch

data class Country(
    val name: String,
    val code: String,
    val capital: String,
    val region: String,
    val currency: Currency,
    val language: Language,
    val flag: String
)

data class Currency(
    val code: String,
    val name: String,
    val symbol: String
)

data class Language(
    val code: String,
    val name: String
)
