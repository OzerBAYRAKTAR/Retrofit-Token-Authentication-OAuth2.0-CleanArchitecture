package com.example.setsiscase.domain.use_case.get_cart

import com.example.setsiscase.domain.repository.SetsisRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val repository: SetsisRepository
) {

}