package com.henryudorji.pokedex.data.remote

import androidx.room.withTransaction
import com.henryudorji.pokedex.data.local.db.PokeDatabase
import com.henryudorji.pokedex.utils.networkBoundResource
import kotlinx.coroutines.delay


class PokeDexRepository(
    private val pokeDexAPI: PokeDexAPI,
    private val pokeDatabase: PokeDatabase
) {
    private val pokeDao = pokeDatabase.pokeDao()

    fun getPokeDex() = networkBoundResource(
        query = {
            pokeDao.getAllPokemon()
        },
        fetch = {
            delay(2000)
            pokeDexAPI.getPokeDex()
        },
        saveFetchedResult = { pokeDex ->
            pokeDatabase.withTransaction {
                pokeDao.deleteAllPokemon()
                pokeDao.insertPokemon(pokemon = pokeDex.pokemon)
            }
        }
    )

    //suspend fun getPokeDex() = pokeDexAPI.getPokeDex()
}