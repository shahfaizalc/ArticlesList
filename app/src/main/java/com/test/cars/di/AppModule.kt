package com.test.cars.di

import android.app.Application
import androidx.room.Room
import com.test.cars.data.database.Database
import com.test.cars.network.ApiService
import com.test.cars.utils.ServiceConstants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(context:Application) =
        Room.databaseBuilder(context, Database::class.java,"CarDatabase")
            .build()

    @Provides
    @Singleton
    fun providesDao(database: Database) =
        database.getDao()

    @Provides
    @Singleton
    fun providesRemoteDao(database: Database) =
        database.remoteKeyDao()

    @Provides
    @Singleton
    fun moshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()!!

    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)

}