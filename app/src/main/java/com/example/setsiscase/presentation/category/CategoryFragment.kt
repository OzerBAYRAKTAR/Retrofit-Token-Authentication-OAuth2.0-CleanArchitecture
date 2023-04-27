package com.example.setsiscase.presentation.category

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.setsiscase.R
import com.example.setsiscase.databinding.FragmentCategoryBinding
import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.presentation.product.ProductActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var viewModel: CategoryViewModel
    private lateinit var adapter: CategoryAdapter
    var list = arrayListOf<CategoryModelUI>()
    private var fragmentBinding: FragmentCategoryBinding?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding= FragmentCategoryBinding.bind(view)
        fragmentBinding=binding

        viewModel= ViewModelProvider(requireActivity()).get(CategoryViewModel::class.java)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getAllCategories()
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
                        adapter.setList(list as ArrayList<CategoryModelUI>)
                    }
                }
                delay(1000)
            }
        }
    }

    private fun recyclerView(){
        adapter= CategoryAdapter(requireContext(),ArrayList())
        fragmentBinding?.categoryRecyclerView?.adapter = adapter
        fragmentBinding?.categoryRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        fragmentBinding!!.categoryRecyclerView.setHasFixedSize(true)
    }


}

