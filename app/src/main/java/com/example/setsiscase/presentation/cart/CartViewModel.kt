package com.example.setsiscase.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.setsiscase.data.repository.room.SetsisRoomRepositoryImp
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.repository.room.SetsisRoomRepository
import com.example.setsiscase.domain.use_case.room_use_case.RoomUseCases
import com.example.setsiscase.util.ResourceUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val usesCases: RoomUseCases,
    val repo: SetsisRoomRepository
): ViewModel(){


    private val state = MutableStateFlow(CartListState())
    var _state : StateFlow<CartListState> = state

    fun getAllCartProducts()=viewModelScope.launch(Dispatchers.IO){
        usesCases.getAllCart.invoke().collect{
            when(it){
                is ResourceUtil.Success ->{
                    state.value = CartListState(infoList = it.data ?: emptyList())
                }
                is ResourceUtil.Loading ->{
                    state.value = CartListState(isLoading = true)
                }
                is ResourceUtil.Error ->{
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
     fun getSumOfCarts(): LiveData<Int> {
        return repo.getTotalCart()
    }
}



