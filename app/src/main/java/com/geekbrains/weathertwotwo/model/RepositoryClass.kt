package com.geekbrains.weathertwotwo.model

 class RepositoryClass: RepositoryInterfece {
     override fun getWeatherFromLocal(): Weather {
         return Weather()
     }

     override fun getWeatherFromServer(): Weather {
         return Weather()
     }
}