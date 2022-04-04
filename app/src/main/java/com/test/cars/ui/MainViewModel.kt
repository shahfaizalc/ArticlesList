package com.test.cars.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.test.cars.data.database.Database
import com.test.cars.data.repository.Car
import com.test.cars.data.repository.CarsRemoteMediator
import com.test.cars.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(private val db: Database, private val apiService: ApiService) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    fun getAllCars() : Flow<PagingData<Car>> = Pager(
        config = PagingConfig(20,enablePlaceholders = false),
        pagingSourceFactory = {db.getDao().getAllCars()},
        remoteMediator = CarsRemoteMediator(db,apiService)
    ).flow.cachedIn(viewModelScope)
}