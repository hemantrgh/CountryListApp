package com.mindbody.countrylistapp.ui.countryinfo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindbody.countrylistapp.databinding.CountryListItemBinding
import com.mindbody.countrylistapp.model.Country

class CountryAdapter(private val countryList: List<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    private lateinit var listener: CountryClickListener
    interface CountryClickListener {
        fun onCountryClick(
            city: Country
        )
    }
    override fun getItemCount() = countryList.size

    fun setOnItemClickListener(listener: CountryClickListener) {
        this.listener = listener
    }

    class CountryViewHolder(val binding: CountryListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CountryListItemBinding.inflate(layoutInflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryList[position]
        holder.binding.model = country
        holder.binding.executePendingBindings()

        holder.binding.root.setOnClickListener {
            listener.onCountryClick(country)
        }
    }
}