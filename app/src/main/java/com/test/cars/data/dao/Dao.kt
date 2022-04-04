package com.test.cars.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.cars.data.repository.Car

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<Car>)

    @Query("SELECT * FROM Car")
    fun getAllCars():PagingSource<Int, Car>

    @Query("DELETE FROM Car")
    fun clearAll()
}