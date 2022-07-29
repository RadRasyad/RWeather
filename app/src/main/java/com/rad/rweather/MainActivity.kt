package com.rad.rweather

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import com.rad.rweather.core.data.Resource
import com.rad.rweather.core.domain.model.CurrentLocation
import com.rad.rweather.core.domain.model.currentforecast.CurrentWeather
import com.rad.rweather.core.domain.model.forecast.Forecast
import com.rad.rweather.core.ui.DailyAdapter
import com.rad.rweather.core.ui.HourlyAdapter
import com.rad.rweather.core.ui.NetworkStatusViewModel
import com.rad.rweather.databinding.ActivityMainBinding
import com.rad.rweather.core.ui.ViewModelFactory
import com.rad.rweather.core.utils.DateFormatter
import com.rad.rweather.core.utils.getLottieSrc
import com.rad.rweather.core.utils.networkstatus.NetworkState
import com.rad.rweather.core.utils.networkstatus.NetworkStatusTracker
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat: Double = 0.0
    private var lon: Double = 0.0

    private val viewModel: NetworkStatusViewModel by lazy {
        ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val networkStatusTracker = NetworkStatusTracker(this@MainActivity)
                    return NetworkStatusViewModel(networkStatusTracker) as T
                }
            },
        )[NetworkStatusViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isLocationPermissionGranted()
        val mLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val mGPS = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (mGPS) {
            getCurrentLocation()
        } else {
            Toast.makeText(this,"Please Turn On the GPS", Toast.LENGTH_LONG).show()
        }

        checkConnection()

        val hourlyAdapter = HourlyAdapter()
        val dailyAdapter = DailyAdapter()


        val factory = ViewModelFactory.getInstance(this)
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        mainViewModel.forecast(lat, lon).observe(this) { forecast ->

            if (forecast != null) {
                when (forecast) {
                    is Resource.Loading -> {
                        binding.constraint.visibility = View.VISIBLE
                        binding.progress.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        binding.constraint.visibility = View.VISIBLE
                        binding.progress.visibility = View.GONE
                        hourlyAdapter.setData(forecast.data?.list)
                        dailyAdapter.setData(forecast.data?.list)
                    }

                    is Resource.Error -> {
                        binding.progress.visibility = View.GONE
                        if (forecast.data?.list?.size != null) {
                            hourlyAdapter.setData(forecast.data.list)
                            dailyAdapter.setData(forecast.data.list)
                            Snackbar.make(binding.root, forecast.message.toString(), Snackbar.LENGTH_LONG).show()
                        } else {
                            binding.constraint.visibility = View.GONE
                            Snackbar.make(binding.root, forecast.message.toString(), Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }

            mainViewModel.currentForecast(lat, lon).observe(this) { forecast ->
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
                            } else {
                                binding.constraint.visibility = View.GONE
                            }
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


    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                0
            )
            false
        } else {
            true
        }
    }

    private fun getCurrentLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val request = LocationRequest.create().apply {
            interval = 100
            fastestInterval = 50
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            maxWaitTime = 100
        }
        val permission = ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permission == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(request, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    val location: Location? = locationResult.lastLocation
                    if (location != null) {
                        lat = location.latitude
                        lon = location.longitude
                    }
                }
            }, null)
        }
    }

    private fun checkConnection() {
        viewModel.state.observe(this) { state ->
            when(state) {
                NetworkState.Error -> Snackbar.make(binding.root, "Disconnected", Snackbar.LENGTH_LONG).show()
                else -> {}
            }
        }
    }
}