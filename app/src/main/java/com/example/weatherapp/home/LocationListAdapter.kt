package com.example.weatherapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.LocationListItemBinding
import com.example.weatherapp.entity.Entry

class LocationListAdapter : ListAdapter<Entry, LocationListAdapter.LocationViewHolder>(DiffCallback) {
    class LocationViewHolder
        (private var binding: LocationListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
                fun bind(location: Entry) {
                    binding.location = location
                    binding.executePendingBindings()
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(LocationListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val loc = getItem(position)
        holder.bind(loc)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Entry>() {
        override fun areItemsTheSame(oldItem: Entry, newItem: Entry): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Entry, newItem: Entry): Boolean {
            return (oldItem.cityName == newItem.cityName
                    && oldItem.stateName == newItem.stateName
                    && oldItem.countryName == newItem.countryName)
        }
    }
}