package com.example.setsiscase.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.use_case.api_use_case.get_home.GetHomeUseCase
import com.example.setsiscase.domain.use_case.room_use_case.RoomUseCases
import com.example.setsiscase.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase,
    private val usesCases: RoomUseCases
): ViewModel() {

    private val state = MutableStateFlow(HomeListState())
    var _state : StateFlow<HomeListState> = state

    fun getRandomProducts()=viewModelScope.launch(Dispatchers.IO){
        getHomeUseCase().collect{
            when(it){
                is Resource.Success ->{
                    state.value = HomeListState(infoList = it.data ?: emptyList())
                }
                is Resource.Loading ->{
                    state.value = HomeListState(isLoading = true)
                }
                is Resource.Error ->{
                    state.value = HomeListState(error = it.message?:"An Unexpected Error")
                }
            }
        }
    }
    fun insertProduct(product: ProductModelUI) {
        viewModelScope.launch {
            usesCases.addCart.invoke(product)
        }
    }

}