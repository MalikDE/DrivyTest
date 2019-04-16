package fr.drivy.malik.app.ui.utils

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import fr.drivy.malik.app.R

/**
 * A view that represent a rating bar using stars icons. Classic RatingBar, from API 1 has some drawback with resizing...
 * Spec :
 * - A filled star icon for each integer values
 * - A half filled star may be used if the rating value has a decimal part which is rounded to 0.5
 * - fill with empty stars until [MAX_RATING]
 *
 * @attr ref android.R.styleable#LinearRatingAttr_fillColor
 */

class ImprovedRatingBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private var fillColor: Int = Color.BLACK
    private var numStars: Int = 5

    init {
        attrs?.let { attributes ->
            val typedArray = context.theme.obtainStyledAttributes(
                attributes,
                R.styleable.ImprovedRatingBarAttr,
                0, 0
            )

            fillColor = typedArray.getColor(R.styleable.ImprovedRatingBarAttr_fillColor, Color.BLACK)
            numStars = typedArray.getInteger(R.styleable.ImprovedRatingBarAttr_numStars, 5)
            typedArray.recycle()
        }

        repeat(numStars) {
            addView(createRatingItem())
        }
    }

    fun setRating(rating: Double) {
        repeat(numStars) {
            val rounded = roundToHalf(rating - it) //rounded decimal part
            if (it < rating.toInt() || rounded == 1.0) {  //full rating
                setResourceId(it, R.drawable.ic_star_black_24dp)
            } else if (rounded == 0.5) {  //half rating
                setResourceId(it, R.drawable.ic_star_half_black_24dp)
            } else {  //empty rating
                setResourceId(it, R.drawable.ic_star_border_black_24dp)
            }
        }
    }

    private fun setResourceId(index: Int, resId: Int) {
        (getChildAt(index) as ImageView).setImageResource(resId)
    }

    private fun createRatingItem() = ImageView(context).apply {
        layoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
        adjustViewBounds = true
        setColorFilter(fillColor)
    }

    private fun roundToHalf(d: Double) = Math.round((d) * 2) / 2.0
}