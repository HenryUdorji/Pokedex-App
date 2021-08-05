package com.henryudorji.pokedex.data.remote

import com.henryudorji.pokedex.data.model.PokeDex
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET


interface PokeDexAPI {

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/"
    }

    @GET("Biuni/PokemonGO-Pokedex/master/pokedex.json")
    suspend fun getPokeDex(): PokeDex
}