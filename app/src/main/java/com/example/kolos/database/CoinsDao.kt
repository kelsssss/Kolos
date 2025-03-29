package com.example.kolos.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinsDao {

    @Query("SELECT * FROM favourite_coin_table")
    fun getAll(): Flow<List<FavouriteCoin>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCoin(favouriteCoin: FavouriteCoin)

    @Query("DELETE FROM favourite_coin_table WHERE id = :id")
    suspend fun deleteCoin(id: String)

    @Query("SELECT COUNT(*) FROM favourite_coin_table WHERE name = :name")
    suspend fun isCoinFavourite(name: String): Int

    @Query("DELETE FROM favourite_coin_table")
    suspend fun deleteAllCoins()
}