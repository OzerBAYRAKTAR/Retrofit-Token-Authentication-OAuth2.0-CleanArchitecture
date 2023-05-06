package com.example.setsiscase.presentation.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.setsiscase.databinding.ItemHomeBinding
import com.example.setsiscase.domain.model.ProductModelUI


class HomeAdapter(var itemList: ArrayList<ProductModelUI>,
                  val itemClickListener: OnItemClickListener): RecyclerView.Adapter<HomeAdapter.ItemHolder>() {


     class ItemHolder(val binding:ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(product: ProductModelUI,clickListener: OnItemClickListener){

                binding.addToCart.setOnClickListener {
                    clickListener.onItemClicked(product)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding= ItemHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val list=itemList[position]
        holder.binding.homeProductName.text="Ürün adı: ${list.productName}"
        holder.binding.homeStockName.text="Kalan stok: ${list.stock.toString()}"
        holder.binding.homePrice.text="Ürün fiyatı: ${list.price.toString()}"
        holder.binding.homeCategoryId.text="Kategori no: ${list.categoryId.toString()}"

        //add to cart
        val product= itemList.get(position)
        holder.bind(product,itemClickListener)

    }

    override fun getItemCount(): Int =itemList.size

    fun setData(list: ArrayList<ProductModelUI>) {
        this.itemList = list
        notifyDataSetChanged()
    }
}
interface OnItemClickListener{
    fun onItemClicked(product: ProductModelUI)
}