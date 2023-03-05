package com.example.hw2

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class HomeworkAdapter(val c: Context, val list:ArrayList<String>): RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder>() {
    inner class HomeworkViewHolder(val v: View): RecyclerView.ViewHolder(v){
        val hw = v.findViewById<TextView>(R.id.hwText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): HomeworkViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val  v = inflater.inflate(R.layout.homework_item,parent,false)
        return HomeworkViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeworkViewHolder, position: Int) {
     val newList = list[position]
        holder.hw.text = newList
    }
}