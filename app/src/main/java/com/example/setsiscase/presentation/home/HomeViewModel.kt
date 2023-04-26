package com.example.setsiscase.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.repository.SetsisRepository
import com.example.setsiscase.domain.use_case.get_home.GetHomeUseCase
import com.example.setsiscase.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase,val repository: SetsisRepository
): ViewModel() {

    private var state = MutableStateFlow(HomeListState())
    var _state: StateFlow<HomeListState> = state


     fun getRandomProducts() = viewModelScope.launch {
        getHomeUseCase().collect {
            when (it) {
                is Resource.Success -> {
                    state.value = HomeListState(infoList = it.data ?: emptyList())
                    Log.d("toCharacter", _state.value.toString())
                }
                is Resource.Loading -> {
                    state.value = HomeListState(isLoading = true)
                    Log.d("loading", it.data.toString())
                }
                is Resource.Error -> {
                    state.value = HomeListState(error =  "An Unexpected Error")
                    Log.d("Error", it.data.toString())
                }
            }
        }
    }
}