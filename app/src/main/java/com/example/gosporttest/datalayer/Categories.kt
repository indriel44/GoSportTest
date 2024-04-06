package com.example.gosporttest.datalayer

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("categories")
    val list: List<Category>
) {
    data class Category(
        @SerializedName("strCategory")
        val name: String
    )
}