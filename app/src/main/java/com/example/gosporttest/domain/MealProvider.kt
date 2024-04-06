package com.example.gosporttest.domain


import com.example.gosporttest.datalayer.MealRepository
import com.example.gosporttest.datalayer.Meals

class MealProvider() {

    private val mealRepository = MealRepository.mealRepository

    suspend fun getMealList(): List<Meals.Meal> {
        val list = emptyList<Meals.Meal>().toMutableList()
        mealRepository.getMealsList().meals.forEach {
            list.add(it)
        }
        return list
    }

    suspend fun getMealListCategory(category:String): List<Meals.Meal> {
        val list = emptyList<Meals.Meal>().toMutableList()
        mealRepository.getMealsListCategory(category).meals.forEach {
            list.add(it)
        }
        return list
    }

    suspend fun getCategoryList(): List<String> {
        val list: MutableList<String> = emptyList<String>().toMutableList()
        mealRepository.getCategoriesList().list.forEach {
            list.add(it.name)
        }
        return list
    }
}