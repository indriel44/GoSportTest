package com.example.gosporttest.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.gosporttest.R
import com.example.gosporttest.datalayer.Meals

import com.example.gosporttest.ui.Adapters.CategoryAdapter
import com.example.gosporttest.ui.Adapters.MainAdapter
import com.example.gosporttest.ui.Adapters.PostersAdapter
import io.paperdb.Paper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private val loadData: () -> Unit = {
        errorTextView.visibility = View.GONE
        viewModel.chosenCategory = ""


        if (viewModel.internetConnected(context)) {
            try {
                viewModel.viewModelScope.launch {
                    viewModel.categoriesList.onEach {
                        categoriesAdapter.submitList(it)

                    }.collect()

                    viewModel.mealList.onEach {
                        it.forEach {meal->
                            Paper.book(LAST_MEALS).write(meal.name, meal)
                        }
                        mealsAdapter.submitList(it)
                    }.collect()
                }
            } catch (error: Throwable) {
                errorTextView.visibility = View.VISIBLE
                errorTextView.text = error.printStackTrace().toString()
            }

        } else {
            val toast =
                Toast.makeText(context, "Проверьте подключение к интернету", Toast.LENGTH_LONG)
            toast.show()
            val list:MutableList<Meals.Meal> = emptyList<Meals.Meal>().toMutableList()

           Paper.book(LAST_MEALS).allKeys.forEach {key->
               Paper.book(LAST_MEALS).read<Meals.Meal>(key)?.let { meal -> list.add(meal) }
           }
            mealsAdapter.submitList(list)

        }
    }

    private val loadDataCategory: (category: String) -> Unit = { category ->
        errorTextView.visibility = View.GONE
        if (viewModel.internetConnected(context)) {
            try {
                viewModel.chosenCategory = category
                viewModel.viewModelScope.launch {
                    viewModel.mealListCategory.onEach {
                        mealsAdapter.submitList(it)
                        categoriesAdapter.notifyDataSetChanged()
                    }.collect()

                }
            } catch (error: Throwable) {
                errorTextView.visibility = View.VISIBLE
                errorTextView.text = error.printStackTrace().toString()
            }

        } else {

            val toast =
                Toast.makeText(context, "Проверьте подключение к интернету", Toast.LENGTH_LONG)
            toast.show()


        }
    }

    private val viewModel = MainViewModel()
    private val postersAdapter = PostersAdapter(listOf(1, 2))
    private val categoriesAdapter = CategoryAdapter(loadData, loadDataCategory, viewModel)
    private val mealsAdapter = MainAdapter()
    private lateinit var errorTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        context?.let { Paper.init(it) };
        super.onViewCreated(view, savedInstanceState)
        errorTextView = view.findViewById(R.id.textView__error_text)

        val postersRecyclerView = view.findViewById<RecyclerView>(R.id.RecyclerView_posters)
        postersRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = postersAdapter
        }

        val categoriesRecyclerView = view.findViewById<RecyclerView>(R.id.RecyclerView_categories)
        categoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter
        }

        val mealsRecyclerView = view.findViewById<RecyclerView>(R.id.RecyclerView_meals)
        mealsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = mealsAdapter
        }


        loadData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    companion object {
        fun newInstance() = MainFragment()
        const val LAST_MEALS="last_meals"
    }
}