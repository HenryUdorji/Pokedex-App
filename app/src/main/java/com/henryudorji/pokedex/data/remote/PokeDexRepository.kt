package com.henryudorji.pokedex.data.remote


class PokeDexRepository(private val pokeDexAPI: PokeDexAPI) {

    suspend fun getPokeDex() = pokeDexAPI.getPokeDex()
}