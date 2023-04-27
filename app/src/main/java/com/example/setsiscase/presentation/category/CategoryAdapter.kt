package com.example.setsiscase.presentation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.setsiscase.databinding.ItemCategoryBinding
import com.example.setsiscase.databinding.ItemHomeBinding
import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.domain.model.ProductModelUI

class CategoryAdapter(var itemList: ArrayList<CategoryModelUI>): RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {


    inner class ItemHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding= ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val list=itemList[position]
        holder.binding.categoryProductName.text="Kategori adı: ${list.categoryName}"
        holder.binding.categoryCategoryId.text="Kategori no: ${list.id.toString()}"
        holder.binding.categoryDate.text="Tarih: ${list.createdDate}"


    }

    override fun getItemCount(): Int =itemList.size

    fun setData(list: ArrayList<CategoryModelUI>) {
        this.itemList = list
        notifyDataSetChanged()
    }

}