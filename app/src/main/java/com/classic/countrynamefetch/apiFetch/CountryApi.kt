package com.classic.countrynamefetch.apiFetch

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryApi {
    // Search countries with capitals (returns a list)
    @GET("countries.json")
    suspend fun getAllCountries(): List<Country>
}