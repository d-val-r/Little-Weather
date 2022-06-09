package com.example.weatherapp.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.database.WeatherDatabase
import com.example.weatherapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // database variables
        val application = requireNotNull(this.activity).application
        val dao = WeatherDatabase.getInstance(application).weatherDatabaseDao

        // data binding
        val binding = FragmentHomeBinding.inflate(inflater)

        // ViewModel variables
        val viewModelFactory = HomeViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        binding.viewModel = viewModel

        // ensure the overflow menu renders
        setHasOptionsMenu(true)


        // create the RecyclerView Adapter
        val adapter = LocationListAdapter()


        binding.locationsList.adapter = adapter

        viewModel.let {
            it.locList.observe(viewLifecycleOwner, Observer{ list ->
                run {
                    binding.locationsList.let {
                        it.adapter?.notifyDataSetChanged()
                    }
                }
            })
        }

        // Inflate the layout for this fragment
        return binding.root
    }


    // ensure the overflow menu connects to the correct XML file
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_location_item) {
            findNavController()
                .navigate(R.id.action_homeFragment_to_overViewFragment)
        } else if (item.itemId == R.id.delete_all_item) {
            viewModel.clear()
        }

        return true
    }
}