package com.example.blog

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var blogPostAdapter: BlogPostAdapter
    private val blogPosts = mutableListOf<BlogPost>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        blogPostAdapter = BlogPostAdapter(blogPosts) { blogPost ->
            val intent = Intent(this, BlogPostDetailActivity::class.java)
            intent.putExtra("ID", blogPost.id)
            intent.putExtra("TITLE", blogPost.title)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = blogPostAdapter

        fetchBlogPosts()
    }

    private fun fetchBlogPosts() {
        RetrofitInstance.api.getBlogPosts().enqueue(object : Callback<List<BlogPost>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<BlogPost>>, response: Response<List<BlogPost>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        blogPosts.clear()
                        blogPosts.addAll(posts)
                        blogPostAdapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e("MainActivity", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<BlogPost>>, t: Throwable) {
                Log.e("MainActivity", "Failure: ${t.message}")
            }
        })
    }
}
