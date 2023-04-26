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
import com.example.setsiscase.data.remote.dto.LoginResponse
import com.example.setsiscase.databinding.FragmentHomeBinding
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.util.Resource


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter
    var list = arrayListOf<ProductModelUI>()
    private var fragmentBinding:FragmentHomeBinding?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentHomeBinding.bind(view)
        fragmentBinding=binding

        viewModel= ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        recyclerViewItems()
        viewModel.getRandomProducts()

        collectData()

    }

    private fun collectData() {
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                _state.collect{ respone ->
                    when {
                        respone.isLoading -> {

                            Toast.makeText(requireContext(), "Lütfen Bekleyin", Toast.LENGTH_SHORT).show()
                        }
                        respone.error.isNotBlank()-> {

                            Toast.makeText(requireContext(), "hata oldu", Toast.LENGTH_LONG).show()
                        }
                        respone.infoList.isNotEmpty()-> {
                            adapter.setData(respone.infoList as ArrayList<ProductModelUI>)
                            fragmentBinding?.homeRecyclerView?.setHasFixedSize(true)
                            Log.e("Resource",  "")

                        }
                    }
                }
            }
        }
    }
    private fun recyclerViewItems() {

        adapter= HomeAdapter(requireContext(),ArrayList())
        fragmentBinding?.homeRecyclerView?.layoutManager=LinearLayoutManager(requireContext())
        fragmentBinding?.homeRecyclerView?.adapter= adapter

    }

}