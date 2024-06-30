package com.example.traveltaipei.data.repository

import com.example.traveltaipei.data.model.ResultData
import com.example.traveltaipei.data.network.RetrofitInstance
import kotlinx.coroutines.flow.flow

class AttractionRepository {
    private val apiService = RetrofitInstance.api

    suspend fun getAttractionsFlow(categoryIds: String, page: Int, lang: String) = flow {
        try {
            val response = apiService.getAttractions(lang, categoryIds, page)
            if (response.isSuccessful) {
                emit(ResultData.Success(data = response.body()))
            } else {
                emit(ResultData.Error(code = response.code(), message = response.message()))
            }
        } catch (e: Exception) {
            emit(ResultData.Error(code = -1, message = e.message))
        }
    }
}
