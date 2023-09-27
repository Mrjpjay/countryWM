package com.example.mycountries.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycountries.data.CountryData
import com.example.mycountries.databinding.CountryItemBinding

class CountryAdapter(private val list: List<CountryData>) :
    RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    lateinit var binding: CountryItemBinding

    inner class CountryHolder(var mBinding: CountryItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
            fun bind(data: CountryData){
                mBinding.txtCapital.text = data.capital
                mBinding.txtCode.text = data.code
                mBinding.txtName.text = data.name
                mBinding.txtRegion.text = data.region
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        binding = CountryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(list[position])
    }
}