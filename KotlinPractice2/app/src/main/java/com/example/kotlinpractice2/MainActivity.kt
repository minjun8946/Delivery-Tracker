package com.example.kotlinpractice2

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    var abc :  ArrayList<DataClass>? = null
    var count = 0
    var name =0
    lateinit var linkname: String // 링크 이름

    lateinit var linknumber : String // 링크 숫자
    lateinit var inter : RetrofitService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tname : EditText = findViewById(R.id.tname)
        val number : EditText = findViewById(R.id.number)
        val start : Button = findViewById(R.id.start)
        val listview1 = findViewById<ListView>(R.id.listview)
        val namelistview = findViewById<ListView>(R.id.namelistview)
        var retronum = 0
        var namenum : Int
        var rechange :Int = 0
        var link : String




        var gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val regionRetrofit = Retrofit.Builder()
            .baseUrl(" https://apis.tracker.delivery/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val server: RetrofitService? = regionRetrofit.create(RetrofitService::class.java)
        val a = server?.getInfo("json", "json", "json")
        var check:ArrayList<DataClass>
        a?.enqueue(object : Callback<ArrayList<DataClass>> {
            override fun onFailure(callback: Call<ArrayList<DataClass>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ArrayList<DataClass>>, response: Response<ArrayList<DataClass>>) {
                println("wow")

                check = response.body()!!



                val UserList1 = arrayListOf<Name>(
                        Name("${check?.get(0)?.name}"),Name("${check?.get(1)?.name}"),Name("${check?.get(2)?.name}"),
                        Name("${check?.get(3)?.name}"),Name("${check?.get(4)?.name}"),Name("${check?.get(5)?.name}"),
                        Name("${check?.get(6)?.name}"),Name("${check?.get(7)?.name}"),Name("${check?.get(8)?.name}"),
                        Name("${check?.get(9)?.name}"),Name("${check?.get(10)?.name}"),Name("${check?.get(11)?.name}"),
                        Name("${check?.get(12)?.name}"),Name("${check?.get(13)?.name}"),Name("${check?.get(14)?.name}"),
                        Name("${check?.get(15)?.name}"),Name("${check?.get(16)?.name}"),Name("${check?.get(17)?.name}"),
                        Name("${check?.get(18)?.name}"),Name("${check?.get(19)?.name}"),Name("${check?.get(20)?.name}"),
                        Name("${check?.get(21)?.name}"),Name("${check?.get(22)?.name}"),Name("${check?.get(23)?.name}"),
                        Name("${check?.get(24)?.name}"),Name("${check?.get(25)?.name}"),Name("${check?.get(26)?.name}"),Name("${check?.get(27)?.name}")
                )
                val Adapter1 = NameAdaptor(this@MainActivity,UserList1)
                namelistview.adapter = Adapter1

                namelistview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    val selection = parent.getItemAtPosition(position) as Name
                    linkname = selection.name
                    rechange=1
                }
                retronum++


            }
        })

            start.setOnClickListener(View.OnClickListener {
                val tone = tname.text.toString()
                linknumber = number.text.toString()


                val regionRetrofit2 = Retrofit.Builder()
                        .baseUrl(" https://apis.tracker.delivery/carriers/" + linkname + "/tracks/" + linknumber + "/")
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
                        //println(response.raw()) )
                        val arr = ab?.progresses?.count()
                        println(ab?.state?.text)
                        println("보내는 사람 :${ab?.from?.name}")
                        println("받는 사람 :${ab?.to?.name}")


                        /* for (i in 0 until arr!!) {
                            println(ab.progresses.get(i).time)
                            println(ab.progresses.get(i).location.name)
                            println(ab.progresses.get(i).description)
                        }*/
                        val UserList = arrayListOf<User>(
                                User("${ab?.progresses?.get(0)?.time}", "${ab?.progresses?.get(0)?.location?.name}", "${ab?.progresses?.get(0)?.description}"),
                                User("${ab?.progresses?.get(1)?.time}", "${ab?.progresses?.get(1)?.location?.name}", "${ab?.progresses?.get(1)?.description}"),
                                User("${ab?.progresses?.get(2)?.time}", "${ab?.progresses?.get(2)?.location?.name}", "${ab?.progresses?.get(2)?.description}"),
                                User("${ab?.progresses?.get(3)?.time}", "${ab?.progresses?.get(3)?.location?.name}", "${ab?.progresses?.get(3)?.description}"),
                                User("${ab?.progresses?.get(4)?.time}", "${ab?.progresses?.get(4)?.location?.name}", "${ab?.progresses?.get(4)?.description}")
                        )
                        val Adapter = UserAdaptor(this@MainActivity, UserList)
                        listview1.adapter = Adapter
                    }

                })


            })

    }


}

