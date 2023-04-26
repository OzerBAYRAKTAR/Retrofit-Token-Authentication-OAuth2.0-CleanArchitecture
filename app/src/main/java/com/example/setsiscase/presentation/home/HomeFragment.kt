package com.example.setsiscase.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.setsiscase.R
import com.example.setsiscase.databinding.FragmentHomeBinding
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.util.Resource


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: HomeViewModel
    private var fragmentBinding:FragmentHomeBinding?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentHomeBinding.bind(view)
        fragmentBinding=binding

        viewModel= ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        collectData()
    }

    private fun collectData() {
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                state.collect{ respone ->
                    when (respone) {
                        is Resource.Loading -> {
                            Toast.makeText(requireContext(), "Please wait", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Success -> {
                            val homeAdapter = HomeAdapter(respone.data as ArrayList<ProductModelUI>)
                            fragmentBinding!!.homeRecyclerView.adapter = homeAdapter
                            fragmentBinding!!.homeRecyclerView.layoutManager =
                                LinearLayoutManager(requireContext())
                            fragmentBinding!!.homeRecyclerView.setHasFixedSize(true)
                        }
                        is Resource.Error -> {
                            Log.e("Resource",  "")
                        }
                    }
                }
            }
        }
    }
}