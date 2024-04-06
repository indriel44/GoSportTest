package com.example.gosporttest.ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gosporttest.R
import com.example.gosporttest.ui.MainFragment
import com.example.gosporttest.ui.MainViewModel
import okhttp3.internal.notifyAll

class CategoryAdapter(
    private val loadData: () -> Unit,
    private val loadDataCategory: (category: String) -> Unit,
    private val viewModel: MainViewModel,
) :
    ListAdapter<String, CategoryAdapter.ViewHolder>(
        DifferentCallback()
    ) {

    private class DifferentCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: String = getItem(position)
        holder.bind(category, loadData, loadDataCategory, viewModel)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val button = view.findViewById<Button>(R.id.Button_category)
        fun bind(
            category: String,
            loadData: () -> Unit,
            loadDataCategory: (category: String) -> Unit,
            viewModel: MainViewModel,
        ) {
            button.text = category
            if (viewModel.chosenCategory == category) {
                button.isSelected = true
                button.setOnClickListener {
                    button.isSelected = false
                    loadData()
                }
            } else {
                button.isSelected = false
                button.setOnClickListener {
                    button.isSelected = true
                    loadDataCategory(category)

                }
            }
        }

    }
}