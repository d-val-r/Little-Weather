package com.example.weatherapp.overview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.databinding.FragmentOverViewBinding


class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentOverViewBinding.inflate(inflater)
        val name = binding.cityName
        val temperature = binding.cityTemp

        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.response.observe(viewLifecycleOwner) {
            if (it[0] == "failed") {
                binding.responseCard.visibility = View.GONE
                binding.weatherCall.text = it[1]
            } else {
                name.text = it[0]
                temperature.text = it[1]
                binding.responseCard.visibility = View.VISIBLE
            }
        }

        binding.searchButton.setOnClickListener {
            var input = binding.locationInput.text.toString()
            if (" " in input) {
                input = input.split(" ").joinToString("")
            }
            Log.i("inputtest", input)
            viewModel.getWeather(input.lowercase())

        }


        return binding.root
    }
}