package com.example.setsiscase.presentation.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.setsiscase.databinding.ItemCartBinding
import com.example.setsiscase.domain.model.ProductModelUI



class CartAdapter():
    RecyclerView.Adapter<CartAdapter.ItemHolder>() {

    fun setList(list: ArrayList<ProductModelUI>) {
        this.itemList = list
        notifyDataSetChanged()
    }

     class ItemHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<ProductModelUI>() {
        override fun areItemsTheSame(oldItem: ProductModelUI, newItem: ProductModelUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductModelUI, newItem: ProductModelUI): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var itemList: List<ProductModelUI>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding= ItemCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val list=itemList[position]
        holder.binding.cartProductName.text=list.productName
        holder.binding.cartPrice.text="${list.price} $"

    }

    override fun getItemCount(): Int = itemList.size
}

