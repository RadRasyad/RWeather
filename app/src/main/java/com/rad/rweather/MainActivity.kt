package com.rad.rweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rad.rweather.core.data.source.remote.network.ApiClient
import com.rad.rweather.databinding.ActivityMainBinding
import com.rad.rweather.core.data.source.remote.response.ForecastResponse
import com.rad.rweather.core.data.source.remote.network.ApiConfig
import com.rad.rweather.core.ui.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //getHourlyForecast()
        val factory = ViewModelFactory.getInstance(this)
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        mainViewModel.forecast(-6.966667, 110.416664)
    }


    private val appID: String
        get() = BuildConfig.API_KEY

    /*
    fun getHourlyForecast() {

        lifecycleScope.launch(Dispatchers.IO) {
            ApiClient.getForecast(-6.966667, 110.416664, appID).enqueue(object :
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

     */

}