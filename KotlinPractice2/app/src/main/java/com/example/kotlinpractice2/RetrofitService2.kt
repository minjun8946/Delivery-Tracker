
package com.example.kotlinpractice2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService2 {
    val a: MainActivity
        get() = MainActivity()

    @GET("./")
    // @Headers( "content-type: application/json")

    fun getInfo2(

            @Query("from") from : Any,
            @Query("to") to: Any,
            @Query("state") state: Any,
            @Query("progresses") progresses: Any,
            @Query("carrier") carrier :Any,
            @Query("massage") message : String
    ) : Call <DataClass2>
}
