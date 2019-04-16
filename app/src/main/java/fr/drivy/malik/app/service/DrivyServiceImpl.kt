package fr.drivy.malik.app.service

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.mde.services.utils.Resource
import fr.drivy.malik.app.model.Car
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DrivyServiceImpl : DrivyService {

    companion object {
        /**
         * Retrofit instance
         */
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/drivy/jobs/master/android/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Drivy API
     */
    private val drivyApi = retrofit.create<DrivyApi>(DrivyApi::class.java)

    override fun requestCars(): LiveData<Resource<List<Car>>> {
        val data = MutableLiveData<Resource<List<Car>>>()
        data.value = Resource.loading(null)

        drivyApi.getCars().enqueue(object : Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                data.value = Resource.success(response.body())
            }

            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                data.value = Resource.error(t.message.toString(), null)
            }
        })

        return data
    }
}