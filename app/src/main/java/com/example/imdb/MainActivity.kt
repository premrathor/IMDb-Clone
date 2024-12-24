package com.example.imdb

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var IMDbCloneAdapter: IMDbCloneAdapter
    private val IMDbClones = mutableListOf<IMDbClone>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        IMDbCloneAdapter = IMDbCloneAdapter(IMDbClones) { IMDbClone ->
            val intent = Intent(this, IMDbCloneDetailActivity::class.java)
            intent.putExtra("ID", IMDbClone.id)
            intent.putExtra("TITLE", IMDbClone.title)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = IMDbCloneAdapter

        fetchIMDbClones()
    }

    private fun fetchIMDbClones() {
        RetrofitInstance.api.getIMDbClones().enqueue(object : Callback<List<IMDbClone>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<IMDbClone>>, response: Response<List<IMDbClone>>) {
                if (response.isSuccessful) {
                    response.body()?.let { clones ->
                        IMDbClones.clear()
                        IMDbClones.addAll(clones)
                        IMDbCloneAdapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e("MainActivity", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<IMDbClone>>, t: Throwable) {
                Log.e("MainActivity", "Failure: ${t.message}")
            }
        })
    }
}
