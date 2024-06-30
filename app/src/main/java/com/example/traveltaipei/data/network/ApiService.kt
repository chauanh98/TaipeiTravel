package com.example.traveltaipei.data.network

import com.example.traveltaipei.data.model.AttractionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("accept: application/json")
    @GET("{lang}/Attractions/All")
    suspend fun getAttractions(
        @Path("lang") lang: String = "en",
        @Query("categoryIds") categoryIds: String,
        @Query("page") page: Int,
    ): Response<AttractionResponse>
}