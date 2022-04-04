package com.test.cars.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.cars.data.dao.Dao
import com.test.cars.data.dao.RemoteKeysDao
import com.test.cars.data.RemoteKeys
import com.test.cars.data.repository.Car

@Database(entities = [Car::class, RemoteKeys::class],version = 1,exportSchema = false)
abstract class Database : RoomDatabase(){
    abstract fun getDao(): Dao
    abstract fun remoteKeyDao(): RemoteKeysDao
}