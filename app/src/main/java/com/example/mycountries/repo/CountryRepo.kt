package com.example.mycountries.repo

import com.example.mycountries.api.CountryService
import com.example.mycountries.data.CountryData

class CountryRepo {
    private val apiService = CountryService.create()

    suspend fun fetch(): List<CountryData>{
        return apiService.fetchCountries()
    }
}