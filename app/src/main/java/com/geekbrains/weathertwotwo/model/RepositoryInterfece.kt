package com.geekbrains.weathertwotwo.model

interface RepositoryInterfece {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocal(): Weather
}