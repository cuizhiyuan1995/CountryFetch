package com.classic.countrynamefetch.apiFetch

import android.util.Log
import java.net.SocketTimeoutException
import javax.inject.Inject


class CountryRepository @Inject constructor(private val api: CountryApi) {
    suspend fun getAllCountries(): List<Country>? {
        return try {
            api.getAllCountries()
        }catch (e: SocketTimeoutException) {
            Log.e("CountryRepository", "Timeout error: ${e.message}")
            null // or return an empty response
        } catch (e: Exception) {
            Log.e("CountryRepository", "Other error: ${e.message}")
            null
        }
    }
}