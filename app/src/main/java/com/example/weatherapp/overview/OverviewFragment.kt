package com.example.weatherapp.overview

import android.os.Bundle
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
        val button = binding.searchButton

        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.response.observe(viewLifecycleOwner) {
            binding.weatherCall.text = it
        }

        binding.searchButton.setOnClickListener {
            viewModel.getWeather()
        }

        viewModel.response


        return binding.root
    }
}