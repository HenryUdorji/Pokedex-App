package com.henryudorji.pokedex.data.local.db

import androidx.room.*
import com.henryudorji.pokedex.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: List<Pokemon>)

    @Query("DELETE FROM pokemon")
    suspend fun deleteAllPokemon()

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): Flow<List<Pokemon>>
}