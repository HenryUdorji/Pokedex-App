package com.henryudorji.pokedex.data.model


import com.google.gson.annotations.SerializedName

data class NextEvolution(
    @SerializedName("name")
    val name: String,
    @SerializedName("num")
    val num: String
)