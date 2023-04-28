package com.example.setsiscase.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.setsiscase.data.source.RoomDb.SetsisDatabase
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.use_case.room_use_case.RoomUseCases
import com.example.setsiscase.presentation.category.CategoryListState
import com.example.setsiscase.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val usesCases: RoomUseCases,
    val database: SetsisDatabase
): ViewModel(){


    private val state = MutableStateFlow(CartListState())
    var _state : StateFlow<CartListState> = state

    fun getAllCartProducts()=viewModelScope.launch(Dispatchers.IO){
        usesCases.getAllCart.invoke().collect{
            when(it){
                is Resource.Success ->{
                    state.value = CartListState(infoList = it.data ?: emptyList())
                }
                is Resource.Loading ->{
                    state.value = CartListState(isLoading = true)
                }
                is Resource.Error ->{
                    state.value = CartListState(error = it.message?:"An Unexpected Error")
                }
            }
        }
    }
    fun deleteProduct(product: ProductModelUI) {
        viewModelScope.launch {
            usesCases.deleteCart.invoke(product)
        }
    }
    fun insertProduct(product: ProductModelUI) {
        viewModelScope.launch {
            usesCases.addCart.invoke(product)
        }
    }
}



