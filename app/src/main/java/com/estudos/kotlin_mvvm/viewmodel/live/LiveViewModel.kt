package com.estudos.kotlin_mvvm.viewmodel.live

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estudos.kotlin_mvvm.models.LiveModel
import com.estudos.kotlin_mvvm.data.webservice.repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LiveViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val liveList = MutableLiveData<List<LiveModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllLives() {

        val response = repository.getAllLives()
        response.enqueue(object : Callback<List<LiveModel>> {
            override fun onResponse(call: Call<List<LiveModel>>, response: Response<List<LiveModel>>) {
                liveList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<LiveModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}