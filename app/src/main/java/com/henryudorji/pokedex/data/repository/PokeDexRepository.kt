package com.henryudorji.pokedex.data.repository

import androidx.room.withTransaction
import com.henryudorji.pokedex.data.local.db.PokeDatabase
import com.henryudorji.pokedex.data.model.Pokemon
import com.henryudorji.pokedex.data.remote.PokeDexAPI
import javax.inject.Inject


class PokeDexRepository @Inject constructor (
    private val pokeDexAPI: PokeDexAPI,
    private val pokeDatabase: PokeDatabase
) {
    private val pokeDao = pokeDatabase.pokeDao()

    suspend fun getPokemonFromApi() = pokeDexAPI.getPokeDex()

    fun getPokemonFromDb() =  pokeDao.getAllPokemon()

    suspend fun deleteAndInsertPokemonFromDb(pokemon: List<Pokemon>) {
        pokeDatabase.withTransaction {
            pokeDao.deleteAllPokemon()
            pokeDao.insertPokemon(pokemon)
        }
    }
}