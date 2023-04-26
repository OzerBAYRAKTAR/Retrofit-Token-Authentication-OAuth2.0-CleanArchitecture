package com.example.setsiscase.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.use_case.get_home.GetHomeUseCase
import com.example.setsiscase.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase,
): ViewModel(){

    private var _state = MutableStateFlow<Resource<List<ProductModelUI>>>(Resource.Loading())
    var state=_state.asStateFlow()

    init {
        getRandomProducts()

    }

    private fun getRandomProducts() =viewModelScope.launch {
        getHomeUseCase().collect{
            _state.emit(it)
        }

    }
}