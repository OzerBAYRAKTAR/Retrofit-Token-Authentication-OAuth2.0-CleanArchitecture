package com.example.setsiscase.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.setsiscase.data.repository.api.SetsisRepositoryImp
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.use_case.api_use_case.get_product.GetProductUseCase
import com.example.setsiscase.domain.use_case.room_use_case.RoomUseCases
import com.example.setsiscase.presentation.product.paging.ProductsPagingSource
import com.example.setsiscase.util.ResourceUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val usesCases: RoomUseCases
): ViewModel() {

    private var categoryId : Int = 0

    var pagingSource: ProductsPagingSource? = null
    get() {
        if (field == null || field?.invalid == true) {
            field = ProductsPagingSource(getProductUseCase,categoryId)
        }
        return field
    }

    val listData = Pager(PagingConfig(pageSize = 1)){
        pagingSource!!
    }.flow.cachedIn(viewModelScope)


    fun insertProduct(product: ProductModelUI) {
        viewModelScope.launch {
            usesCases.addCart.invoke(product)
        }
    }
    fun submitQuery(catId:Int){
        categoryId= catId
        pagingSource?.invalidate()
    }

}