package com.geekbrains.weathertwotwo.ui.main

import com.geekbrains.weathertwotwo.model.Weather

sealed class AppState {
    data class Success(val weatherDate: Weather):AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()


}
