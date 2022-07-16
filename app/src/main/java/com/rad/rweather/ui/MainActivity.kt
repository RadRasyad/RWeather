package com.rad.rweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.rad.rweather.BuildConfig
import com.rad.rweather.databinding.ActivityMainBinding
import com.rad.rweather.model.remote.RemoteRepository
import com.rad.rweather.model.remote.response.ForecastResponse
import com.rad.rweather.network.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getHourlyForecast()
    }

    private val apiConfig = ApiConfig
    private val appID: String
        get() = BuildConfig.API_KEY

    fun getHourlyForecast() {

        lifecycleScope.launch(Dispatchers.IO) {
            apiConfig.create().getHourlyForecast(-6.966667, 110.416664, appID, 8).enqueue(object :
                Callback<ForecastResponse> {
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    Log.d("Response Success", response.body()?.cnt.toString())
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    Log.d("Response Failed", t.message.toString())
                }

            })
        }


    }

}