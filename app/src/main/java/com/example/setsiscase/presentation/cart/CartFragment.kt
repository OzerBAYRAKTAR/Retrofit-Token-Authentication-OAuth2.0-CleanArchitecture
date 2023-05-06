package com.example.setsiscase.presentation.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setsiscase.R
import com.example.setsiscase.databinding.FragmentCartBinding
import com.example.setsiscase.databinding.FragmentCategoryBinding
import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.presentation.MainActivity
import com.example.setsiscase.presentation.category.CategoryAdapter
import com.example.setsiscase.presentation.category.CategoryViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CartFragment : Fragment(R.layout.fragment_cart) {


    private var fragmentBinding: FragmentCartBinding?= null
    private lateinit var viewModel : CartViewModel
    private lateinit var cartadapter: CartAdapter
    private var totalCart:Int=0
    var list = arrayListOf<ProductModelUI>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding= FragmentCartBinding.bind(view)
        fragmentBinding=binding

        viewModel= ViewModelProvider(requireActivity()).get(CartViewModel::class.java)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getAllCartProducts()
        }
        getSumOfCart()


        //for delete with swipe
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteProduct(cartadapter.itemList[position])

                Snackbar.make(requireView(),"Sepetten Kaldırıldı", Snackbar.LENGTH_LONG)
                    .setAction("İptal Et",View.OnClickListener {
                        viewModel.insertProduct(cartadapter.itemList[position])
                        cartadapter.setList(list)   //if we dont do that, recyclerview not showing current list after inserted data
                    }).show()
            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.cartRecyclerView)

        recyclerView()
        getCarts()

    }
    private fun getCarts(){
        CoroutineScope(Dispatchers.Main).launch {
            viewModel._state.collect{value->
                when {
                    value.isLoading -> {
                    }
                    value.error.isNotBlank() -> {
                    }
                    value.infoList.isNotEmpty() -> {
                        list.addAll(value.infoList)
                        cartadapter.setList(list as ArrayList<ProductModelUI>)
                    }
                }
            }
        }
    }
    private fun recyclerView(){
        cartadapter= CartAdapter()
        fragmentBinding?.cartRecyclerView?.adapter = cartadapter
        fragmentBinding?.cartRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        fragmentBinding!!.cartRecyclerView.setHasFixedSize(true)
    }
    private fun getSumOfCart() {
        viewModel.getSumOfCarts().observe(viewLifecycleOwner, Observer {
            it?.let{
                totalCart=it
                fragmentBinding!!.cartSum.text="${totalCart} $"

            }
        })
    }

}



