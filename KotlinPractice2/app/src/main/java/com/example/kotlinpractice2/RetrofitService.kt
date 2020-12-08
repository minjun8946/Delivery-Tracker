package com.example.kotlinpractice2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("carriers/")
   // @Headers( "content-type: application/json")
    fun getInfo(
        @Query("id") id: String,
        @Query("name") path: String,
        @Query("tel") tel: String
    ) : Call<ArrayList<DataClass>>
}