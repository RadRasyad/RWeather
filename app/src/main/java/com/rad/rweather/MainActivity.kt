package com.rad.rweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rad.rweather.core.data.Resource
import com.rad.rweather.core.data.source.local.room.ForecastDao
import com.rad.rweather.core.data.source.local.room.ForecastDatabase
import com.rad.rweather.core.data.source.remote.network.ApiResponse
import com.rad.rweather.core.data.source.remote.response.ForecastResponse
import com.rad.rweather.core.domain.model.Forecast
import com.rad.rweather.core.domain.model.ListForecast
import com.rad.rweather.core.ui.DailyAdapter
import com.rad.rweather.core.ui.HourlyAdapter
import com.rad.rweather.databinding.ActivityMainBinding
import com.rad.rweather.core.ui.ViewModelFactory
import com.rad.rweather.core.utils.DateFormatter
import com.rad.rweather.core.utils.getLottieSrc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hourlyAdapter = HourlyAdapter()
        val dailyAdapter = DailyAdapter()

        val factory = ViewModelFactory.getInstance(this)
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        mainViewModel.forecast(-6.966667, 110.416664).observe(this) { forecast ->
            if (forecast != null) {
                when (forecast) {
                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        binding.constraint.visibility = View.VISIBLE
                        lifecycleScope.launch(Dispatchers.Main) {
                            forecast.data?.let { setCurrentForecast(it) }

                            Log.d("Data List", forecast.data?.list?.size.toString())
                            hourlyAdapter.setData(forecast.data?.list)
                            dailyAdapter.setData(forecast.data?.list)

                        }
                    }

                    is Resource.Error -> {
                        Snackbar.make(binding.root, "Error", Snackbar.LENGTH_LONG)
                    }
                }
            }

        }

        with(binding.rvHourly) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = hourlyAdapter
        }

        with(binding.rvDaily) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = dailyAdapter
        }

    }


    private fun setCurrentForecast(forecast: Forecast) {
        binding.apply {
            val currentForecast = forecast.list?.get(0)

            tvCity.text = forecast.city?.name
            tvTemp.text = currentForecast?.main?.temp?.toInt().toString() + "°"

            val date = currentForecast?.dateText
            tvDate.text = date?.let { DateFormatter.getDayNHour(it) }

            val img = currentForecast?.weather?.get(0)?.icon

            lavWeather.setAnimation(img?.let { getLottieSrc(it) })
            lavWeather.playAnimation()
            lavWeather.loop(true)


        }
    }


}