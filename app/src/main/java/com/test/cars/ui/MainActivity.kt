package com.test.cars.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.cars.utils.DateTimeUtil.is24HourFormat
import com.test.cars.adapter.CarsAdapter
import com.test.cars.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var carsAdapter: CarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiRecyclerView()

        lifecycleScope.launchWhenStarted {
            mainViewModel.getAllCars().collectLatest { response->
                binding.apply {
                    recyclerview.isVisible = true
                    progressBar.isVisible = false
                }
                Log.d(TAG, "onCreate: $response")
                setTimeFormat()
                carsAdapter.submitData(response)
            }
        }

    }

    private fun setTimeFormat() {
        is24HourFormat = DateFormat.is24HourFormat(applicationContext)
    }

    private fun intiRecyclerView() {
        Log.d(TAG, "intiRecyclerView ")
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = carsAdapter
            }
        }
    }
}