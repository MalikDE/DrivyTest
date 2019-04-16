package fr.drivy.malik.app.ui.home

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fr.drivy.malik.app.R
import fr.drivy.malik.app.model.Car
import fr.drivy.malik.app.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.car_item.view.*

class CarAdapter : RecyclerView.Adapter<CarAdapter.CarHolder>() {
    class CarHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Car) {
            with(view) {

                //start DetailActivity on click with item clicked passed as extra
                setOnClickListener {
                    val intent = Intent(
                        it.context,
                        DetailActivity::class.java
                    ).apply {
                        putExtra(DetailActivity.EXTRA_DETAIL_ITEM, item)
                    }

                    it.context.startActivity(intent)
                }

                //update content
                Picasso.get()
                    .load(item.picture_url)
                    .placeholder(R.drawable.car_placeholder)
                    .into(itemView.car_item_image)
                itemView.car_item_rating.setRating(item.rating.average)
                itemView.car_item_title.text = "${item.brand} ${item.model}"
                itemView.car_rating_count.text = item.rating.count.toString()
                itemView.car_item_price.text =
                    view.context.getString(R.string.car_price_per_day, item.price_per_day.toString())

            }
        }
    }

    private val items = mutableListOf<Car>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarAdapter.CarHolder {
        return CarHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.car_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CarAdapter.CarHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setContent(updateList: List<Car>?) {
        items.clear()
        updateList?.let { items.addAll(it) }
        notifyDataSetChanged()
    }
}