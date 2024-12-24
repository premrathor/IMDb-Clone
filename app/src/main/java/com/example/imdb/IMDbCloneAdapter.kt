package com.example.imdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.R

class IMDbCloneAdapter(private val IMDbClones: List<IMDbClone>, private val itemClick: (IMDbClone) -> Unit) :
    RecyclerView.Adapter<IMDbCloneAdapter.IMDbCloneViewHolder>() {

    class IMDbCloneViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val IdTextView: TextView = view.findViewById(R.id.Id)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IMDbCloneViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_imdb_clone, parent, false)
        return IMDbCloneViewHolder(view)
    }

    override fun onBindViewHolder(holder: IMDbCloneViewHolder, position: Int) {
        val IMDbClone = IMDbClones[position]
        holder.IdTextView.text = IMDbClone.id
        holder.titleTextView.text = IMDbClone.title

        holder.itemView.setOnClickListener { itemClick(IMDbClone) }
    }

    override fun getItemCount() = IMDbClones.size
}
