package com.example.mycountries.view

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycountries.R
import com.example.mycountries.data.CountryData
import com.example.mycountries.databinding.ActivityMainBinding
import com.example.mycountries.repo.CountryRepo
import com.example.mycountries.viewModel.CountryViewModel
import com.example.mycountries.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        observers()
    }

    private fun init() {
        val repo = CountryRepo()
        val vmFactory = ViewModelFactory(repo)

        viewModel = ViewModelProvider(this, vmFactory)[CountryViewModel::class.java]
        binding.countryRecycler.layoutManager = LinearLayoutManager(this)
    }

    private fun observers() {
        viewModel.fetchCountries()

        viewModel.countries.observe(this, ::countries)
        viewModel.loading.observe(this, ::loading)
        viewModel.error.observe(this, ::errorMsg)
    }

    private fun errorMsg(b: Boolean) {
        if (b) {
            Toast.makeText(this, getString(R.string.an_error_occurred), Toast.LENGTH_SHORT).show()
        }
    }

    private fun loading(b: Boolean) {
        if (b) {
            binding.progress.visibility = View.VISIBLE
        } else {
            binding.progress.visibility = View.INVISIBLE
        }
    }

    private fun countries(countryData: List<CountryData>) {
        val list = mutableListOf<CountryData>()
        countryData.forEach { country ->
            list.add(
                CountryData(
                    country.name,
                    country.region,
                    country.code,
                    country.capital
                )
            )
        }
        val adapter = CountryAdapter(list)
        binding.countryRecycler.adapter = adapter
    }
}