package com.example.setsiscase.presentation.product

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.setsiscase.data.remote.dto.Product
import com.example.setsiscase.databinding.ItemProductsBinding
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.presentation.home.OnItemClickListener
import javax.inject.Inject


class ProductAdapter @Inject constructor(
    val itemClickListener: OnItemClickListenerProduct
): PagingDataAdapter<ProductModelUI, ProductAdapter.ItemHolderProduct>(diffCallBack) {

    private lateinit var binding: ItemProductsBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolderProduct {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemProductsBinding.inflate(inflater,parent,false)
        context = parent.context
        return ItemHolderProduct()

    }

    override fun onBindViewHolder(holder: ItemHolderProduct, position: Int) {
        holder.bind(getItem(position)!!,itemClickListener)
        holder.setIsRecyclable(false)
    }

    inner class ItemHolderProduct() :
        RecyclerView.ViewHolder(binding.root) {

         fun bind(product: ProductModelUI,clickListener: OnItemClickListenerProduct){
             binding.apply {
                 productProductName.text = "Ürün adı: ${product.productName}"
                 productStockName.text="Kalan stok: ${product.stock.toString()}"
                 productPrice.text="Ürün fiyatı: ${product.price.toString()}"
                 productCategoryId.text="Kategori no: ${product.categoryId.toString()}"
             }

             binding.addToCartProductFragment.setOnClickListener {
                 clickListener.onItemClicked(product)
             }
         }
    }


    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<ProductModelUI>() {
            override fun areItemsTheSame(oldItem: ProductModelUI, newItem: ProductModelUI): Boolean {
                return oldItem.categoryId == newItem.categoryId
            }

            override fun areContentsTheSame(oldItem: ProductModelUI, newItem: ProductModelUI): Boolean {
                return oldItem == newItem
            }
        }
    }
}
interface OnItemClickListenerProduct{
    fun onItemClicked(product: ProductModelUI)
}
