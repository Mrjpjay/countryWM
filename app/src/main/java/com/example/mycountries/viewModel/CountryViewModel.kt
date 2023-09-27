package com.example.mycountries.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycountries.data.CountryData
import com.example.mycountries.repo.CountryRepo
import kotlinx.coroutines.launch
import java.lang.Exception

class CountryViewModel(private val repo: CountryRepo) : ViewModel() {

    private var _countries = MutableLiveData<List<CountryData>>()
    var countries: LiveData<List<CountryData>> = _countries

    private var _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    private var _error = MutableLiveData<Boolean>()
    var error: LiveData<Boolean> = _error

    fun fetchCountries() {
        _loading.value = true
        viewModelScope.launch {
            try {
                _countries.value = repo.fetch()
                _loading.value = false
                _error.value = false
            } catch (e: Exception) {
                _error.value = true
                _loading.value = false
            }
        }
    }
}