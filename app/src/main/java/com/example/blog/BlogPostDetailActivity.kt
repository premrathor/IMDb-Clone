package com.example.blog

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BlogPostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_post)

        val id = intent.getStringExtra("ID")
        val title = intent.getStringExtra("TITLE")

        findViewById<TextView>(R.id.detailIdTextView).text = id
        findViewById<TextView>(R.id.detailTitleTextView).text = title
    }
}
