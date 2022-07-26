package com.rad.rweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.snackbar.Snackbar
import com.rad.rweather.core.data.Resource
import com.rad.rweather.core.domain.model.currentforecast.CurrentWeather
import com.rad.rweather.core.domain.model.forecast.Forecast
import com.rad.rweather.core.ui.DailyAdapter
import com.rad.rweather.core.ui.HourlyAdapter
import com.rad.rweather.databinding.ActivityMainBinding
import com.rad.rweather.core.ui.ViewModelFactory
import com.rad.rweather.core.utils.DateFormatter
import com.rad.rweather.core.utils.getLottieSrc
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


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
                        binding.constraint.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        binding.constraint.visibility = View.VISIBLE

                        hourlyAdapter.setData(forecast.data?.list)
                        dailyAdapter.setData(forecast.data?.list)
                    }

                    is Resource.Error -> {
                        if (forecast.data?.list?.size != null) {

                            hourlyAdapter.setData(forecast.data.list)
                            dailyAdapter.setData(forecast.data.list)
                            Snackbar.make(binding.root, "Tidak Ada Koneksi", Snackbar.LENGTH_LONG)
                        } else {
                            binding.constraint.visibility = View.GONE
                        }
                        Snackbar.make(binding.root, "Error", Snackbar.LENGTH_LONG)
                    }
                }
            }

            mainViewModel.currentForecast(-6.966667, 110.416664).observe(this) { forecast ->
                if (forecast!=null) {
                    when (forecast) {
                        is Resource.Loading -> {
                            binding.constraint.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.constraint.visibility = View.VISIBLE

                            forecast.data?.let { setCurrentForecast(it) }
                        }

                        is Resource.Error -> {

                            if (forecast.data?.weather?.size != null) {
                                setCurrentForecast(forecast.data)
                                Snackbar.make(binding.root, forecast.message.toString(), Snackbar.LENGTH_LONG)
                            } else {
                                binding.constraint.visibility = View.GONE
                            }
                            //Snackbar.make(binding.root, "Error", Snackbar.LENGTH_LONG)
                        }
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


    private fun setCurrentForecast(forecast: CurrentWeather) {
        binding.apply {

            tvCity.text = forecast.name
            tvTemp.text = forecast.main?.temp?.toInt().toString() + "°"

            val date = forecast.date?.toLong()?.let { DateFormatter.getDayNHour(it) }
            tvDate.text = date

            val img = forecast.weather?.get(0)?.icon
            lavWeather.setAnimation(img?.let { getLottieSrc(it) })
            lavWeather.playAnimation()
            lavWeather.repeatMode = LottieDrawable.INFINITE

        }
    }



}