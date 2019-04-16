package fr.drivy.malik.app.ui.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import fr.drivy.malik.app.R
import fr.drivy.malik.app.model.Car
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL_ITEM = "EXTRA_DETAIL_ITEM"
    }

    private val carItem: Car? by lazy { intent?.extras?.getParcelable(EXTRA_DETAIL_ITEM) as Car }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        detail_up_navigation.setOnClickListener { finishAfterTransition() }

        carItem?.let { updateContentView(it) }
    }

    private fun updateContentView(item: Car) {
        // car
        detail_car_rating.setRating(item.rating.average)
        detail_car_title.text = "${item.brand} ${item.model}"
        detail_car_rating_count.text = "(${item.rating.count})"
        detail_car_price.text = getString(R.string.detail_price_per_day, item.price_per_day.toString())
        Picasso.get()
            .load(item.picture_url)
            .placeholder(R.drawable.car_placeholder)
            .into(detail_car_picture)

        // owner
        detail_owner_name.text = item.owner.name
        detail_owner_rating_count.text = "(${item.owner.rating.count})"
        detail_owner_rating.setRating(item.owner.rating.average)
        Picasso.get()
            .load(item.owner.picture_url)
            .placeholder(R.drawable.ic_account_circle_black_24dp)
            .into(detail_owner_picture)
    }
}