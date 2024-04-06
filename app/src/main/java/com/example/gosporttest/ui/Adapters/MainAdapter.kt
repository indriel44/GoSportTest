package com.example.gosporttest.ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gosporttest.R
import com.example.gosporttest.datalayer.Meals

class MainAdapter  : ListAdapter<Meals.Meal, MainAdapter.ViewHolder>(
    DifferentCallback()
) {
    private class DifferentCallback : DiffUtil.ItemCallback< Meals.Meal>() {
        override fun areItemsTheSame(oldItem: Meals.Meal, newItem:  Meals.Meal) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem:  Meals.Meal, newItem:  Meals.Meal) =
            oldItem == newItem
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal: Meals.Meal = getItem(position)
        holder.bind(meal)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameTextView = view.findViewById<TextView>(R.id.textView__mealcard_name)
        private val descriptionTextView = view.findViewById<TextView>(R.id.textView__mealcard_description)
        private val priceTextView = view.findViewById<TextView>(R.id.textView__mealcard_price)
        private val imageView = view.findViewById<ImageView>(R.id.imageView__mealcard)

        fun bind(meal:  Meals.Meal) {
            nameTextView.text=meal.name
            descriptionTextView.text=meal.description
            priceTextView.text=" от ${meal.cost.toString()} руб"
            imageView.load(meal.picture)

        }

    }
}