package com.example.imdb

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IMDbCloneDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imdb_clone)

        val id = intent.getStringExtra("ID")
        val title = intent.getStringExtra("TITLE")

        findViewById<TextView>(R.id.detailIdTextView).text = id
        findViewById<TextView>(R.id.detailTitleTextView).text = title
    }
}
