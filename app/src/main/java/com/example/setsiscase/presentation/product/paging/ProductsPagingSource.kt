package com.example.setsiscase.presentation.product.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.setsiscase.data.remote.dto.Product
import com.example.setsiscase.data.remote.dto.toProductModelUI
import com.example.setsiscase.data.repository.api.SetsisRepositoryImp
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.use_case.api_use_case.get_product.GetProductUseCase
import retrofit2.HttpException


class ProductsPagingSource (
    private val getProductUseCase: GetProductUseCase,
    private val categoryId: Int
    ):PagingSource<Int,ProductModelUI>() {



    override fun getRefreshKey(state: PagingState<Int, ProductModelUI>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductModelUI> {

        return try {

            val currentPage = params.key ?: 1
            val previewPage = if(currentPage == 1) null else -1
            val response = getProductUseCase.invoke(categoryId, currentPage)
            val data = response.body()?.products?.map {
                it.toProductModelUI()
            } ?: emptyList()
            val responseData = mutableListOf<ProductModelUI>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = previewPage,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }


}