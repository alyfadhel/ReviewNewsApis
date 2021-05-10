package com.example.apinewsreview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apinewsreview.R
import com.example.apinewsreview.pojo.ArticlesItem

class NewsAdapter(var news: List<ArticlesItem?>?): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_news,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return news?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = news?.get(position)
        holder.title.text = items?.title
        holder.date.text = items?.publishedAt
        holder.desc.text = items?.description
        Glide.with(holder.itemView).load(items?.urlToImage).into(holder.image)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.title)
        val date: TextView = itemView.findViewById(R.id.date)
        val desc: TextView = itemView.findViewById(R.id.desc)
        val image: ImageView = itemView.findViewById(R.id.image)
    }
    fun changeData(news: List<ArticlesItem?>?){
        this.news = news
        notifyDataSetChanged()
    }
}