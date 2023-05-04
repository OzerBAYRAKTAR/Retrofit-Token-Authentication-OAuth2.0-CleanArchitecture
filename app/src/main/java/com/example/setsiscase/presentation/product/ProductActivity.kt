package com.example.setsiscase.presentation.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.setsiscase.databinding.ActivityProductBinding
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductActivity : AppCompatActivity(),OnItemClickListenerProduct {

    private lateinit var binding : ActivityProductBinding
    private lateinit var viewModel: ProductsViewModel
    private var productadapter: ProductAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel= ViewModelProvider(this).get(ProductsViewModel::class.java)

        if (intent != null) {
            val id = intent.getIntExtra("id", 0)
            Log.d("insideIntent", id.toString())
            loadData(id)
            viewModel.submitQuery(id)
        }

        recyclerView()
        goBack()

    }
    private fun loadData(id: Int){
        lifecycleScope.launchWhenCreated {
            viewModel.listData.collectLatest{
                productadapter?.submitData(it)
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
        productadapter= ProductAdapter(this)
        binding.productRecyclerView.adapter = productadapter
        binding.productRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.productRecyclerView.setHasFixedSize(true)
    }


    override fun onItemClicked(product: ProductModelUI) {
       viewModel.insertProduct(product)
        Toast.makeText(this, "Sepete eklendi ${product.productName}", Toast.LENGTH_SHORT).show()
    }


}