package fr.drivy.malik.app.ui.home

import android.arch.lifecycle.ViewModel
import fr.drivy.malik.app.service.DrivyService
import fr.drivy.malik.app.service.DrivyServiceImpl

class HomeViewModel : ViewModel() {
    private val drivyService: DrivyService = DrivyServiceImpl()

    //LiveData that exposes car list data
    val carsLiveData = drivyService.requestCars()
}