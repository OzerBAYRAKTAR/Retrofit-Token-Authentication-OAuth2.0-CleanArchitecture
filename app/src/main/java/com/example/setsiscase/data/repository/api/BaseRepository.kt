package com.example.setsiscase.data.repository.api

import com.example.setsiscase.data.source.api.SetsisApi

abstract class BaseRepository(private val api: SetsisApi) : SafeApiCall {

}