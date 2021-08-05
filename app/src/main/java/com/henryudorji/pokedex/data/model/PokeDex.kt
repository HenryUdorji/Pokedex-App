package com.henryudorji.pokedex.data.model


import com.google.gson.annotations.SerializedName

data class PokeDex(
    @SerializedName("pokemon")
    val pokemon: List<Pokemon>
)