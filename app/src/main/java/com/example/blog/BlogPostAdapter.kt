package com.example.blog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BlogPostAdapter(private val blogPosts: List<BlogPost>, private val itemClick: (BlogPost) -> Unit) :
    RecyclerView.Adapter<BlogPostAdapter.BlogPostViewHolder>() {

    class BlogPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val IdTextView: TextView = view.findViewById(R.id.Id)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogPostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_blog_post, parent, false)
        return BlogPostViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlogPostViewHolder, position: Int) {
        val blogPost = blogPosts[position]
        holder.IdTextView.text = blogPost.id
        holder.titleTextView.text = blogPost.title

        holder.itemView.setOnClickListener { itemClick(blogPost) }
    }

    override fun getItemCount() = blogPosts.size
}
