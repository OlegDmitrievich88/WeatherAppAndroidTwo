package com.geekbrains.weathertwotwo.model


 class RepositoryClass: RepositoryInterfece {
//     override fun getWeatherFromLocal(): Weather {
//         return Weather()
//     }

     override fun getWeatherFromServer(): Weather {
         return Weather()
     }

     override fun getWeatherFromLocalRus(): List<Weather> { // берет и возвращает список из городов и вот это из weather
         return getRussianCities()
     }

     override fun getWeatherFromLocalWorldCity(): List<Weather> { // берет и возвращает список из городов и вот это из weather
         return getWorldCities()
     }
 }