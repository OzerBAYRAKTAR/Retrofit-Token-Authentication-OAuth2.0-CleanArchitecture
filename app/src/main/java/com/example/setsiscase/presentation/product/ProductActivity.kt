package com.example.setsiscase.presentation.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.setsiscase.databinding.ActivityProductBinding
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductActivity : AppCompatActivity(),OnItemClickListenerProduct {
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
        goBack()
    }
    private fun callSetsisApi(CategoryId:Int){
        CoroutineScope(Dispatchers.Main).launch {
                viewModel._state.collect{value->
                    when {
                        value.isLoading -> {
                            binding.productProgressBar.visibility= View.VISIBLE
                            binding.productError.visibility= View.INVISIBLE
                        }
                        value.error.isNotBlank() -> {
                            binding.productProgressBar.visibility= View.INVISIBLE
                            binding.productError.visibility= View.VISIBLE
                            Log.d("iderror",CategoryId.toString())
                        }
                        value.infoList.isNotEmpty() -> {
                            binding.productProgressBar.visibility=View.INVISIBLE
                            binding.productError.visibility=View.INVISIBLE
                            list.addAll(value.infoList)
                            adapter.setData(list as ArrayList<ProductModelUI>)
                        }
                    }
                    delay(400)
                }
            }
        }
    private fun goBack() {
        binding.productBack.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

    private fun recyclerView(){
        adapter= ProductAdapter(ArrayList(),this)
        binding.productRecyclerView.adapter = adapter
        binding.productRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.productRecyclerView.setHasFixedSize(true)
    }

    override fun onItemClicked(product: ProductModelUI) {
        viewModel.insertProduct(product)
        Toast.makeText(this, "Sepete eklendi ${product.productName}", Toast.LENGTH_SHORT).show()
    }

}