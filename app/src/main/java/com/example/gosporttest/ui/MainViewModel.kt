package com.example.gosporttest.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.gosporttest.datalayer.Meals
import com.example.gosporttest.domain.InternetConncetionChecker
import com.example.gosporttest.domain.MealProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val mealProvider = MealProvider()
    lateinit var chosenCategory:String


    val categoriesList: Flow<List<String>> = channelFlow {
        withContext(Dispatchers.IO) {
            send(mealProvider.getCategoryList())
        }
    }

    val mealList: Flow<List<Meals.Meal>> = channelFlow {
        withContext(Dispatchers.IO) {
            send(mealProvider.getMealList())
        }
    }

    val mealListCategory: Flow<List<Meals.Meal>> = channelFlow {
        withContext(Dispatchers.IO) {
            send(mealProvider.getMealListCategory(chosenCategory))
        }
    }

    fun internetConnected(context: Context?): Boolean =
        InternetConncetionChecker.isInternetAvailable(context)


}