package com.example.mycountries.api

import com.example.mycountries.data.CountryData
import com.example.mycountries.utils.BASE_URL
import com.example.mycountries.utils.COUNTRY_END_POINT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountryService {

    @GET(COUNTRY_END_POINT)
    suspend fun fetchCountries(): List<CountryData>

    companion object{
        fun create(): CountryService{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CountryService::class.java)
        }
    }
}