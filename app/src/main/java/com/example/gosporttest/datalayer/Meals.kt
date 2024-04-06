package com.example.gosporttest.datalayer


import com.google.gson.annotations.SerializedName


data class Meals(
    @SerializedName("meals")
    val meals: List<Meal>,
) {

    data class Meal(


        @SerializedName("strMeal")
        val name: String,

        @SerializedName("strInstructions")
        val description: String,

        @SerializedName("idMeal")

        val cost: Int,

        @SerializedName("strMealThumb")
        val picture: String,

        @SerializedName("strCategory")
        val category: String,
    )
}