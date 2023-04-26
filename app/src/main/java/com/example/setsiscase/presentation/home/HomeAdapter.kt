package com.example.setsiscase.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.setsiscase.databinding.ItemHomeBinding
import com.example.setsiscase.domain.model.ProductModelUI


class HomeAdapter(private val context: Context, var itemList: ArrayList<ProductModelUI>): RecyclerView.Adapter<HomeAdapter.ItemHolder>() {


    inner class ItemHolder(val binding:ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding= ItemHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val list=itemList[position]
        holder.binding.homeProductName.text=list.productName
        holder.binding.homeStockName.text=list.stock.toString()
        holder.binding.homePrice.text=list.price.toString()
        holder.binding.homeCategoryId.text=list.categoryId.toString()

    }

    override fun getItemCount(): Int =itemList.size

    fun setData(itemList:ArrayList<ProductModelUI>)
    {
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }


}