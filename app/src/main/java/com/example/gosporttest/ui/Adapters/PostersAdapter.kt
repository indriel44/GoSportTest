package com.example.gosporttest.ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gosporttest.R

class PostersAdapter(private val list: List<Int>) :
    RecyclerView.Adapter<PostersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.poster_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  list.count()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number: Int = list[position]
        holder.bind(number)

    }

   class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val image = view.findViewById<ImageView>(R.id.image)

        fun bind(value: Int) {
            if (value==1) image.setImageResource(R.drawable.poster_1)
            else image.setImageResource(R.drawable.poster_2)
        }
    }
}