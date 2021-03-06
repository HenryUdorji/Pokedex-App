package com.henryudorji.pokedex.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
@Entity
data class Pokemon(
    @SerializedName("height")
    val height: String,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("num")
    val num: String,
    val type: List<String>,
    @SerializedName("weaknesses")
    val weaknesses: List<String>,
    @SerializedName("weight")
    val weight: String
): Parcelable