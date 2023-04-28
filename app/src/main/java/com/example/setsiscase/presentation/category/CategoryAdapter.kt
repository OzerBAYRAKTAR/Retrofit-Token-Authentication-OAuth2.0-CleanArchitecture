package com.example.setsiscase.presentation.category

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.setsiscase.databinding.ItemCategoryBinding
import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.presentation.product.ProductActivity



class CategoryAdapter(private val context: Context, var itemList: ArrayList<CategoryModelUI>): RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {

    fun setList(list: ArrayList<CategoryModelUI>) {
        this.itemList = list
        notifyDataSetChanged()
    }

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

        holder.binding.linearCategory.setOnClickListener {
            val intent= Intent(context,ProductActivity::class.java)
            intent.putExtra("id",list.id)
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int =itemList.size
}