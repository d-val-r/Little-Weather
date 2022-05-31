package com.example.weatherapp.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentOverViewBinding


class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentOverViewBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)


        return binding.root
    }
}