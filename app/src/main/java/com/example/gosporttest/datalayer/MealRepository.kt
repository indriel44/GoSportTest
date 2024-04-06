package com.example.gosporttest.datalayer

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealRepository {

    @GET("search.php?s")
    suspend fun getMealsList():Meals

    @GET("filter.php?c")
    suspend fun getMealsListCategory(@Query("c") category: String,):Meals

    @GET("categories.php")
    suspend fun getCategoriesList():Categories

    companion object{
        val mealRepository:MealRepository=createMealRepository()

        private fun createMealRepository(): MealRepository{
            val loggingInterceptor= HttpLoggingInterceptor().apply {
                level=HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder().apply {
                addNetworkInterceptor(loggingInterceptor)
            }.build()

            val retrofit = Retrofit.Builder().apply {
                client(client)
                addConverterFactory(GsonConverterFactory.create())
                baseUrl("https://themealdb.com/api/json/v1/1/")
            }.build()

            return retrofit.create(MealRepository::class.java)
        }
    }
}