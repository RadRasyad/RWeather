package com.rad.rweather.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rad.rweather.core.domain.model.forecast.ListForecast
import com.rad.rweather.core.utils.DateFormatter
import com.rad.rweather.core.utils.ForecastSort
import com.rad.rweather.core.utils.getLottieSrc
import com.rad.rweather.databinding.HourlyForecastRowBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HourlyAdapter: RecyclerView.Adapter<HourlyAdapter.DailyViewHolder>() {

    private var listForecast = ArrayList<ListForecast>()

    fun setData(data: List<ListForecast>?) {
        if (data==null) return

        listForecast.clear()
        listForecast.addAll(ForecastSort.sortHourly(data))
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view = HourlyForecastRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val data = listForecast[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listForecast.size

    inner class DailyViewHolder(private val binding: HourlyForecastRowBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(forecast: ListForecast) {
            binding.apply {
                val date = forecast.dateText?.let { DateFormatter.getHour(it) }
                tvHour.text = date
                tvTemp.text = forecast.main?.temp?.toInt().toString() + "°"
                val img = forecast.weather?.get(0)?.icon
                CoroutineScope(Dispatchers.Default).launch {
                    val path = getLottieSrc(img!!)
                    withContext(Dispatchers.Main) {
                        lavWeather.setAnimation(path)
                    }
                }
            }
        }

    }
}