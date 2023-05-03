package com.example.setsiscase.presentation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.setsiscase.data.remote.dto.CategoryModel
import com.example.setsiscase.data.source.api.Resource
import com.example.setsiscase.domain.use_case.api_use_case.get_category.GetCategoryUseCase
import com.example.setsiscase.util.ResourceUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase
): ViewModel() {

    private val state = MutableStateFlow(CategoryListState())
    var _state : StateFlow<CategoryListState> = state

    fun getAllCategories()=viewModelScope.launch(Dispatchers.IO){
        getCategoryUseCase().collect{
            when(it){
                is ResourceUtil.Success ->{
                    state.value = CategoryListState(infoList = it.data ?: emptyList())
                }
                is ResourceUtil.Loading ->{
                    state.value = CategoryListState(isLoading = true)
                }
                is ResourceUtil.Error ->{
                    state.value = CategoryListState(error = it.message?:"An Unexpected Error")
                }
            }
        }
    }

}