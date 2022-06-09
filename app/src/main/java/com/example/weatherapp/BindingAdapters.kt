package com.example.weatherapp

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.entity.Entry
import com.example.weatherapp.home.LocationListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Entry>?) {
    val adapter = recyclerView.adapter as LocationListAdapter
    adapter.submitList(data)
}