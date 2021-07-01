package com.example.newslive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items:ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentitem = items[position]
        holder.title.text = currentitem.title
        holder.author.text = currentitem.author
        Glide.with(holder.itemView.context).load(currentitem.imageUrl).into(holder.img)
    }

    override fun getItemCount(): Int {
       return items.size
    }
    fun updateNews(updatedNews: ArrayList<News>) {
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val title: TextView = itemView.findViewById(R.id.title)
  val author: TextView = itemView.findViewById(R.id.author)
  val img: ImageView = itemView.findViewById(R.id.image)
}

interface NewsItemClicked {
    fun onItemClicked(item: News)
}