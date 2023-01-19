package com.example.kotlinretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity() {


    val BASE_URL = "https://jsonplaceholder.typicode.com/"

    lateinit var myAdapter: MyAdapter
    lateinit var linearlayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerviewUsers)
        recyclerView.setHasFixedSize(true)
        linearlayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearlayoutManager

        getmyData()
    }

    private fun getmyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<myDataItem>?> {
            override fun onResponse(
                call: Call<List<myDataItem>?>,
                response: Response<List<myDataItem>?>
            ) {
                val responseBody = response.body()!!
                    myAdapter = MyAdapter(baseContext,responseBody)
                    myAdapter.notifyDataSetChanged()
                    recyclerView.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<myDataItem>?>, t: Throwable) {
                 d("MainActivity", "onFailure: "+t.message)
            }
        })
    }
}