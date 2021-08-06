package com.henryudorji.pokedex.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.henryudorji.pokedex.data.model.NextEvolution
import com.henryudorji.pokedex.data.model.PrevEvolution


class PokeConverter {
    @TypeConverter
    fun fromNextEvolutionList(value: List<NextEvolution>): String {
        val gson = Gson()
        val type = object : TypeToken<List<NextEvolution>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toNextEvolutionList(value: String): List<NextEvolution> {
        val gson = Gson()
        val type = object : TypeToken<List<NextEvolution>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromPrevEvolutionList(value: List<PrevEvolution>): String {
        val gson = Gson()
        val type = object : TypeToken<List<PrevEvolution>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toPrevEvolutionList(value: String): List<PrevEvolution> {
        val gson = Gson()
        val type = object : TypeToken<List<PrevEvolution>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }
}