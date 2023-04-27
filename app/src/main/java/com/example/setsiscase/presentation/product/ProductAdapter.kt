package com.example.setsiscase.presentation.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.setsiscase.databinding.ItemHomeBinding
import com.example.setsiscase.databinding.ItemProductsBinding
import com.example.setsiscase.domain.model.ProductModelUI


class ProductAdapter(var itemList: ArrayList<ProductModelUI>): RecyclerView.Adapter<ProductAdapter.ItemHolder>() {


    inner class ItemHolder(val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding= ItemProductsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val list=itemList[position]
        holder.binding.productProductName.text="Ürün adı: ${list.productName}"
        holder.binding.productStockName.text="Kalan stok: ${list.stock.toString()}"
        holder.binding.productPrice.text="Ürün fiyatı: ${list.price.toString()}"
        holder.binding.productCategoryId.text="Kategori no: ${list.categoryId.toString()}"

    }

    override fun getItemCount(): Int =itemList.size

    fun setData(list: ArrayList<ProductModelUI>) {
        this.itemList = list
        notifyDataSetChanged()
    }

}