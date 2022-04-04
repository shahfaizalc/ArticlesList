package com.test.cars.network

import com.test.cars.data.repository.CarsResponseData
import com.test.cars.utils.ServiceConstants.ARTICLE_LIST
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET(ARTICLE_LIST)
    suspend fun getAllCars(
        @Query("page") page:Int,
        @Query("limit") limit:Int
    ) : CarsResponseData
}