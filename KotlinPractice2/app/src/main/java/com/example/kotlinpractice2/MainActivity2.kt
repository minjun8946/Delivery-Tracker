package com.example.kotlinpractice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val linkname  = intent.getStringExtra("name")
        val linknumber = intent.getStringExtra("number")
        val listview1 = findViewById<ListView>(R.id.listview)
        val stats = findViewById<TextView>(R.id.stats)

        if(linkname !="" && linknumber!="") {
            val regionRetrofit2 = Retrofit.Builder()
                    .baseUrl("https://apis.tracker.delivery/carriers/$linkname/tracks/$linknumber/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val server2: RetrofitService2 = regionRetrofit2.create(RetrofitService2::class.java)
            val b = server2.getInfo2("json", "json", "json", "json", "json", "json") // 두번째 RETROFIT
            b.enqueue(object : Callback<DataClass2> {
                override fun onFailure(callback: Call<DataClass2>, t: Throwable) {
                    t.printStackTrace()
                    println("no")
                }

                override fun onResponse(call: Call<DataClass2>, response: Response<DataClass2>) {
                    println("wow2")

                    val ab: DataClass2? = response.body()

                     val arr = ab?.progresses?.count()
                    println("보내는 사람 :${ab?.from?.name}")
                    println("받는 사람 :${ab?.to?.name}")
                    println(arr)
                    if(arr!=null) {
                        stats.text = ab.state.text
                    }
                    if(arr==null){
                        stats.text = "잘못된 번호입니다!"
                    }

                    val UserList = mutableListOf<User>()
                    if (arr != null) {
                        for(i in 0 until arr) {
                            UserList.add(
                                    User(ab.progresses.get(i).time, ab.progresses.get(i).location.name, ab.progresses.get(i).description)
                            )
                        }
                    }
                        val Adapter = UserAdaptor(this@MainActivity2, UserList as ArrayList<User>)
                        listview1.adapter = Adapter


                }

            })
        }
    }
}