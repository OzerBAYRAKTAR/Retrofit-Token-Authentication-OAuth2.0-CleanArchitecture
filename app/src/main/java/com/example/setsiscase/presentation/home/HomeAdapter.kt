package com.example.setsiscase.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.setsiscase.databinding.ItemHomeBinding
import com.example.setsiscase.domain.model.ProductModelUI


class HomeAdapter(var itemList: ArrayList<ProductModelUI>): RecyclerView.Adapter<HomeAdapter.ItemHolder>() {


    inner class ItemHolder(private val binding:ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductModelUI) {
            with(binding){
                with(item) {
                    homeProductName.text= products.map { it.productName }.toString()
                    homeStockName.text = products.map { it.stock }.toString()
                    homePrice.text = products.map{ it.price }.toString()
                    homeCategoryId.text= products.map{ it.categoryId}.toString()

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding=
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int =itemList.size


}