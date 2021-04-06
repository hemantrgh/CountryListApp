package com.mindbody.countrylistapp.ui.countryinfo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindbody.countrylistapp.databinding.ProvinceListItemBinding
import com.mindbody.countrylistapp.model.Province

class ProvinceAdapter(private val provinceList: List<Province>) : RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHolder>() {
    override fun getItemCount() = provinceList.size

    class ProvinceViewHolder(val binding: ProvinceListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProvinceListItemBinding.inflate(layoutInflater, parent, false)
        return ProvinceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        val province = provinceList[position]
        holder.binding.model = province
        holder.binding.executePendingBindings()
    }
}