package fr.drivy.malik.app.service

import android.arch.lifecycle.LiveData
import com.mde.services.utils.Resource
import fr.drivy.malik.app.model.Car

interface DrivyService {
    /**
     * Request all cars
     * @return a [LiveData] representing the request
     */
    fun requestCars(): LiveData<Resource<List<Car>>>
}