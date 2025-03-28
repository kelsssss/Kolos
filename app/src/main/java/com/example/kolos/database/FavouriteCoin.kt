package com.example.kolos.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favourite_coin_table")
data class FavouriteCoin (
    @PrimaryKey val id: String = "",
    val image: String = "",
    val name: String = "",
    val symbol: String = ""
)