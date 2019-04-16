package fr.drivy.malik.app.ui.home

// Rename the Pair class from the Android framework to avoid a name clash

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.mde.services.utils.Status
import fr.drivy.malik.app.R
import fr.drivy.malik.app.model.Car
import fr.drivy.malik.app.utils.observe
import kotlinx.android.synthetic.main.activity_home.*
import android.util.Pair as UtilPair

class HomeActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }
    private val adapter = CarAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        home_progressBar.visibility = View.VISIBLE

        val layoutManager = LinearLayoutManager(this)
        home_recycler.layoutManager = layoutManager
        home_recycler.adapter = adapter

        viewModel.carsLiveData.observe(this) {
            when (it?.status) {
                Status.SUCCESS -> displayCarList(it.data)
                Status.ERROR -> displayError(it.message)
                Status.LOADING -> { }
            }
        }
    }

    private fun displayCarList(cars: List<Car>?) {
        home_progressBar.visibility = View.GONE
        adapter.setContent(cars)
    }

    private fun displayError(message: String?) {
        Toast.makeText(this, "Error : $message", Toast.LENGTH_LONG).show()
    }
}
