package com.estudos.kotlin_mvvm.data.webservice.repositories

import com.estudos.kotlin_mvvm.data.webservice.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllLives() = retrofitService.getAllLives()

}