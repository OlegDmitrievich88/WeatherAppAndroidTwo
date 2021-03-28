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
    fun getLiveData(): LiveData<AppState> = liveDataToObserve
    fun getWeatherFromLocal(){
        getDataFromLocal()
    }
    //fun getWeatherFromRemote() = getDataFromLocal()

    private fun getDataFromLocal(){
        liveDataToObserve.value = AppState.Loading
        Thread{
            sleep(1000)
            val  data = repository.getWeatherFromLocal()
            liveDataToObserve.postValue(
                AppState.Success(data)
            )
        }.start()
    }
}
