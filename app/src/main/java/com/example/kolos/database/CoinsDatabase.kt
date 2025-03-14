package com.example.kolos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FavouriteCoin::class], version = 1)
abstract class CoinsDatabase : RoomDatabase() {
    abstract fun coinsDao(): CoinsDao

    companion object {
        @Volatile
        private var INSTANCE: CoinsDatabase? = null
        fun getInstance(context: Context): CoinsDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CoinsDatabase::class.java,
                        "Coins_db"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}