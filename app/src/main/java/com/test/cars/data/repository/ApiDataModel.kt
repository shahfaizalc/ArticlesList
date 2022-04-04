package com.test.cars.data.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * API response data
 *
 * @param content
 * @param status
 */
data class CarsResponseData(
    val status: String,
    val content: List<Car>
)

/**
 * Car data table
 *
 * @param dateTime
 * @param id
 * @param image
 * @param ingress
 * @param title
 */
@Entity(tableName = "Car")
data class Car(
    @PrimaryKey
    val id: String,
    val title: String,
    val dateTime: String,
    val ingress: String,
    val image: String
)

