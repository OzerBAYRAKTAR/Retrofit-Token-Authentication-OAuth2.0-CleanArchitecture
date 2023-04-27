package com.example.setsiscase.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.setsiscase.R
import com.example.setsiscase.databinding.FragmentHomeBinding
import com.example.setsiscase.domain.model.ProductModelUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment(R.layout.fragment_home),OnItemClickListener {

    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter
    var list = arrayListOf<ProductModelUI>()
    private var fragmentBinding:FragmentHomeBinding?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentHomeBinding.bind(view)
        fragmentBinding=binding

        viewModel= ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getRandomProducts()
        }
        recyclerView()
        callAPI()

    }

    private fun callAPI(){
        CoroutineScope(Dispatchers.Main).launch {
                viewModel._state.collect{value->
                    when {
                        value.isLoading -> {
                        }
                        value.error.isNotBlank() -> {
                        }
                        value.infoList.isNotEmpty() -> {
                            list.addAll(value.infoList)
                            adapter.setData(list as ArrayList<ProductModelUI>)
                        }
                    }
                    delay(1000)
                }
            }
        }
    private fun recyclerView(){
        adapter= HomeAdapter(ArrayList(),this)
        fragmentBinding?.homeRecyclerView?.adapter = adapter
        fragmentBinding?.homeRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        fragmentBinding!!.homeRecyclerView.setHasFixedSize(true)
      }



    override fun onItemClicked(product: ProductModelUI) {
        viewModel.insertProduct(product)
        Toast.makeText(requireContext(), "favoriye eklendi ${product.productName}", Toast.LENGTH_SHORT).show()
    }
}



