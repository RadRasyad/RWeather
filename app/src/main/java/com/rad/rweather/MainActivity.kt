package com.rad.rweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rad.rweather.core.data.Resource
import com.rad.rweather.core.data.source.remote.network.ApiResponse
import com.rad.rweather.core.data.source.remote.response.ForecastResponse
import com.rad.rweather.core.domain.model.Forecast
import com.rad.rweather.core.domain.model.ListForecast
import com.rad.rweather.core.ui.HourlyAdapter
import com.rad.rweather.databinding.ActivityMainBinding
import com.rad.rweather.core.ui.ViewModelFactory
import com.rad.rweather.core.utils.DateFormatter
import com.rad.rweather.core.utils.getLottieSrc
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

        val factory = ViewModelFactory.getInstance(this)
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        mainViewModel.forecast(-6.966667, 110.416664).observe(this) { forecast ->
            if (forecast != null) {
                when (forecast) {
                    is Resource.Loading -> {
                        binding.constraint.visibility = View.GONE
                    }

                    is Resource.Success -> {
                        binding.constraint.visibility = View.VISIBLE
                        lifecycleScope.launch(Dispatchers.IO) {
                            forecast.data?.let { setCurrentForecast(it) }
                            //hourlyAdapter.setData(forecast.data?.list)
                        }
                    }

                    is Resource.Error -> {
                        binding.constraint.visibility = View.GONE
                    }
                }
            }

        }

        with(binding.rvHourly) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = hourlyAdapter
        }
    }


    private fun setCurrentForecast(forecast: Forecast) {
        binding.apply {
            val currentForecast = forecast.list?.get(0)

            tvCity.text = forecast.city?.name
            tvTemp.text = currentForecast?.main?.temp.toString()+"°"

            val date = currentForecast?.dateText
            tvDate.text = date?.let { DateFormatter.getDayNHour(it) }

            val img = currentForecast?.weather?.get(0)?.icon
            lavWeather.setAnimation(getLottieSrc(img!!))
        }
    }


}