package com.geekbrains.weathertwotwo.model

interface RepositoryInterfece {
    fun getWeatherFromServer(): Weather // берем погоду с сервера
   // fun getWeatherFromLocal(): Weather
    fun  getWeatherFromLocalRus(): List<Weather> // берем погоду для русских городов из локального хранилища
    fun  getWeatherFromLocalWorldCity(): List<Weather> //берем погоду для мировых городов из локального хранилища
}