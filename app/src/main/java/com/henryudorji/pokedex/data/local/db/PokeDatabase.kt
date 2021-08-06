
package com.henryudorji.pokedex.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.henryudorji.pokedex.data.model.Pokemon

@TypeConverters(PokeConverter::class)
@Database(
    entities = [Pokemon::class],
    version = 1,
    exportSchema = false
)
abstract class PokeDatabase : RoomDatabase() {

    abstract fun pokeDao(): PokeDao

}

