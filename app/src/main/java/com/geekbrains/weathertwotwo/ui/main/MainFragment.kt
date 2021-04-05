package com.geekbrains.weathertwotwo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.geekbrains.weathertwotwo.R
import com.geekbrains.weathertwotwo.databinding.CityListFragmentBinding

import com.google.android.material.snackbar.Snackbar
//homework
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: CityListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private var isDataSetRus: Boolean = true
    private val adapter = MainFragmentAdapter()

//    private var _binding: FragmentMainBinding? = null
//    private val binding get() = _binding!!
//
//    private lateinit var viewModel: MainViewModel
//    private val adapter = MainFragmentAdapter()
//    private var isDataSetRus: Boolean = true




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.city_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener { changeWeatherDataSet() }
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(
                viewLifecycleOwner,
                { state:AppState -> renderData(state) }
        )
        viewModel.getWeatherFromLocalSourceRus()
    }

    private fun changeWeatherDataSet() {
      if (isDataSetRus){
          viewModel.getWeatherFromLocalSourceWorld()
          binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
      } else {
          viewModel.getWeatherFromLocalSourceRus()
          binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
      }
        isDataSetRus = !isDataSetRus
    }
//    if (isDataSetRus) {
//        viewModel.getWeatherFromLocalSourceWorld()
//        binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
//    } else {
//        viewModel.getWeatherFromLocalSourceRus()
//        binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
//    }
//    isDataSetRus = !isDataSetRus
//}

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.mainFragmentRecyclerView.adapter = adapter
//        binding.mainFragmentFAB.setOnClickListener { changeWeatherDataSet() }
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
//        viewModel.getWeatherFromLocalSourceRus()
//    }


    private fun renderData(appState: AppState) {
        when(appState){
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                Snackbar
                       .make(binding.mainFragmentFAB, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                        .setAction(getString(R.string.reload)) { viewModel.getWeatherFromLocalSourceRus() }
                        .show()

            }
            AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                adapter.setWeather(appState.weatherDate)
            }
        }
    }
//    private fun renderData(appState: AppState) {
//        when (appState) {
//            is AppState.Success -> {
//                binding.mainFragmentLoadingLayout.visibility = View.GONE
//                adapter.setWeather(appState.weatherData)
//            }
//            is AppState.Loading -> {
//                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
//            }
//            is AppState.Error -> {
//                binding.mainFragmentLoadingLayout.visibility = View.GONE
//                Snackbar
//                        .make(binding.mainFragmentFAB, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
//                        .setAction(getString(R.string.reload)) { viewModel.getWeatherFromLocalSourceRus() }
//                        .show()
//            }
//        }
//    }


    //    private fun setData(weatherData: Weather) {
//        binding.cityName.text = weatherData.city.city
//        binding.cityCoordinates.text = String.format(
//                getString(R.string.coordinats),
//                weatherData.city.lat.toString(),
//                weatherData.city.lon.toString()
//        )
//        binding.tempValue.text = weatherData.temperature.toString()
//        binding.feelTemp.text = weatherData.feelslike.toString()
//
//    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}