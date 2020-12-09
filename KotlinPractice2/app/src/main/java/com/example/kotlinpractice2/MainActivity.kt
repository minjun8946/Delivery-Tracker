package com.example.kotlinpractice2

import android.content.Intent
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
    var linkname = "" // 링크 이름
    var linknumber ="" // 링크 숫자
    lateinit var linkname1 : String
    lateinit var inter : RetrofitService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tname : TextView = findViewById(R.id.tname)
        val number : EditText = findViewById(R.id.number)
        val start : Button = findViewById(R.id.start)
        val listview1 = findViewById<ListView>(R.id.listview)
        val namelistview = findViewById<ListView>(R.id.namelistview)
        var rechange =0
        var link = ""




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
                val UserList1 = mutableListOf<Name>()
                for(i in 0..27) {
                    UserList1.add(
                            Name(check.get(i).name)

                    )
                }
                val Adapter1 = NameAdaptor(this@MainActivity, UserList1 as ArrayList<Name>)
                namelistview.adapter = Adapter1

                namelistview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    val selection = parent.getItemAtPosition(position) as Name
                    link = selection.name
                    for(i in 0..27){
                        if(link == check.get(i).name){
                            rechange =i
                            break;
                        }
                    }
                    linkname = check.get(rechange).id
                    tname.setText(check.get(rechange).name)
                }


            }
        })


                start.setOnClickListener(View.OnClickListener {
                    linknumber = number.text.toString()
                    println(linkname)
                    println(linknumber)
                    if(linknumber!="" && linkname !="") {
                        val intent = Intent(this, MainActivity2::class.java)
                       intent.putExtra("name", linkname,)
                        intent.putExtra("number", linknumber)
                        startActivity(intent)
                    }
                })

    }
}

