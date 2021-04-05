package com.geekbrains.weathertwotwo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.weathertwotwo.model.RepositoryClass
import com.geekbrains.weathertwotwo.model.RepositoryInterfece
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: RepositoryInterfece = RepositoryClass()
) : ViewModel() {
   // fun getLiveData(): LiveData<AppState> = liveDataToObserve
    fun getLiveData() = liveDataToObserve
    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(isRussian = true)
    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussian = false)
    //fun getWeatherFromRemoteSource() = getDataFromLocalSource(isRussian = true)

    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread{
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(if (isRussian) repository.getWeatherFromLocalRus() else
                repository.getWeatherFromLocalWorldCity()))
        }.start()
    }

}
//fun getLiveData() = liveDataToObserve
//
//fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(isRussian = true)
//
//fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussian = false)
//
//fun getWeatherFromRemoteSource() = getDataFromLocalSource(isRussian = true)
//
//private fun getDataFromLocalSource(isRussian: Boolean) {
//    liveDataToObserve.value = AppState.Loading
//    Thread {
//        sleep(1000)
//        liveDataToObserve.postValue(AppState.Success(if (isRussian) repositoryImpl.getWeatherFromLocalStorageRus() else repositoryImpl.getWeatherFromLocalStorageWorld()))
//    }.start()
//}
