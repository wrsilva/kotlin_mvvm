package com.estudos.kotlin_mvvm.viewmodel.live

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estudos.kotlin_mvvm.data.webservice.repositories.MainRepository

class LiveViewModelFactory constructor(private val repository: MainRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LiveViewModel::class.java)) {
            LiveViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}