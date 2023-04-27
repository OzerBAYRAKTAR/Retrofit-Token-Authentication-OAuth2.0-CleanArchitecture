package com.example.setsiscase.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.setsiscase.domain.use_case.get_home.GetHomeUseCase
import com.example.setsiscase.domain.use_case.get_product.GetProductUseCase
import com.example.setsiscase.presentation.home.HomeListState
import com.example.setsiscase.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase
): ViewModel() {

    private val state = MutableStateFlow(ProductListState())
    var _state : StateFlow<ProductListState> = state

    fun getRandomProducts(CategoryId: Int, pageNumber: Int)=viewModelScope.launch(Dispatchers.IO){
        getProductUseCase(CategoryId, pageNumber).collect{
            when(it){
                is Resource.Success ->{
                    state.value = ProductListState(infoList = it.data ?: emptyList())
                }
                is Resource.Loading ->{
                    state.value = ProductListState(isLoading = true)
                }
                is Resource.Error ->{
                    state.value = ProductListState(error = it.message?:"An Unexpected Error")
                }
            }
        }
    }

}