package com.test.cars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.cars.data.repository.Car
import com.test.cars.utils.DateTimeUtil
import com.test.cars.databinding.EachRowBinding
import javax.inject.Inject

class CarsAdapter
@Inject
constructor() : PagingDataAdapter<Car, CarsAdapter.CarsViewHolder>(DiffUtils) {

    object DiffUtils : DiffUtil.ItemCallback<Car>(){
        override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem == newItem
        }
    }

    class CarsViewHolder(private val binding: EachRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(cars: Car){
            binding.nameText.text = cars.title
            binding.apply {
                carImage.load(cars.image)
                nameText.text = cars.title
                descriptionText.text = DateTimeUtil.getFullDateDisplay(cars.dateTime)
                starCountText.text = cars.ingress
            }
        }
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        val cars = getItem(position)
        cars?.let {
            holder.bind(cars)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        return CarsViewHolder(EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}