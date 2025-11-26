package com.classic.countrynamefetch.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.classic.countrynamefetch.apiFetch.Country
import com.classic.countrynamefetch.apiFetch.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {

    var fetchResults by mutableStateOf<List<Country>>(emptyList())
        private set

    var networkerror by mutableStateOf(false)
        private set

    fun searchCountry() {
        viewModelScope.launch {
            if(repository.getAllCountries() != null){
                fetchResults = repository.getAllCountries()!!
                //Log.d("MainViewModel",fetchResults.toString())
            }
            else{
                networkerror = true
            }
        }
    }

    fun setNetworkError(value: Boolean) {
        networkerror = value
    }
}