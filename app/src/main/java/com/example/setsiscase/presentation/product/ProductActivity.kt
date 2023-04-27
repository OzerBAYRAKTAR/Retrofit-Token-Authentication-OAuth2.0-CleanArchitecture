package com.example.setsiscase.presentation.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.setsiscase.databinding.ActivityProductBinding
import com.example.setsiscase.domain.model.ProductModelUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProductBinding
    private lateinit var viewModel: ProductsViewModel
    var list = arrayListOf<ProductModelUI>()
    private lateinit var adapter: ProductAdapter
    private var pageNumber= listOf(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel= ViewModelProvider(this).get(ProductsViewModel::class.java)

        if(intent!=null){
            val id = intent.getIntExtra("id",0)
            Log.d("insideIntent",id.toString())
            viewModel.getProductById(id,pageNumber)
            callSetsisApi(id)
        }
        recyclerView()
    }
    private fun callSetsisApi(CategoryId:Int){
        CoroutineScope(Dispatchers.Main).launch {
                viewModel._state.collect{value->
                    when {
                        value.isLoading -> {
                            Log.d("idloading", CategoryId.toString())
                        }
                        value.error.isNotBlank() -> {
                            Log.d("iderror",CategoryId.toString())
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
        adapter= ProductAdapter(ArrayList())
        binding.productRecyclerView.adapter = adapter
        binding.productRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.productRecyclerView.setHasFixedSize(true)
    }

    }