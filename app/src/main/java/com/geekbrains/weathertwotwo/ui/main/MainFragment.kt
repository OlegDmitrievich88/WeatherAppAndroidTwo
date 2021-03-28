package com.geekbrains.weathertwotwo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.geekbrains.weathertwotwo.R
import com.geekbrains.weathertwotwo.databinding.MainFragmentBinding
import com.geekbrains.weathertwotwo.model.Weather
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(
                viewLifecycleOwner,
                Observer { state:AppState -> renderData(state) }
        )
        viewModel.getWeatherFromLocal()
    }

    private fun renderData(appState: AppState) {
        when(appState){
            is AppState.Error -> {
                binding.loading.visibility = View.GONE
                Snackbar
                        .make(binding.main, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                        .setAction(getString(R.string.error)) { viewModel.getWeatherFromLocal() }
                        .show()

            }
            AppState.Loading -> {
                binding.loading.visibility = View.VISIBLE
            }
            is AppState.Success -> {
              val weatherData = appState.weatherDate
                binding.loading.visibility = View.GONE
                setData(weatherData)
            }
        }
    }

    private fun setData(weatherData: Weather) {
        binding.cityName.text = weatherData.city.city
        binding.cityCoordinates.text = String.format(
                getString(R.string.coordinats),
                weatherData.city.lat.toString(),
                weatherData.city.lon.toString()
        )
        binding.tempValue.text = weatherData.temperature.toString()
        binding.feelTemp.text = weatherData.feelslike.toString()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}