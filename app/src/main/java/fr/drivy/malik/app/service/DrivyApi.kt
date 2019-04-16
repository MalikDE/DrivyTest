package fr.drivy.malik.app.service

import fr.drivy.malik.app.model.Car
import retrofit2.Call
import retrofit2.http.GET

interface DrivyApi {

    @GET("cars.json")
    fun getCars(): Call<List<Car>>
}