package fr.drivy.malik.app.ui.utils

import android.content.Context
import android.graphics.*
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import fr.drivy.malik.app.R

/**
 * A Circular ImageView util.
 *
 * CircularImageAttr_borderColor : set a stroke color around the ImageView
 * CircularImageAttr_borderWidth : set the stroke width
 *
 * @attr ref android.R.styleable#CircularImageAttr_borderColor
 * @attr ref android.R.styleable#CircularImageAttr_borderWidth
 */
class CircularImageView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0) : AppCompatImageView(context, attrs, defStyle) {

    private val _paint = Paint()

    private var strokeWidth: Int = 0
    private val strokePaint = Paint()

    private var enableBorder = true

    private var original: Bitmap? = null
    private val originalCanvas = Canvas()
    private var shader: Shader? = null

    init {

        attrs?.let { attributes ->
            val typedArray = context.theme.obtainStyledAttributes(
                    attributes,
                    R.styleable.CircularImageAttr,
                    0, 0)

            strokeWidth = typedArray.getDimensionPixelOffset(R.styleable.CircularImageAttr_borderWidth, 0)
            if (strokeWidth > 0) {
                strokePaint.color = typedArray.getColor(R.styleable.CircularImageAttr_borderColor, Color.WHITE)
            }
            typedArray.recycle()
        }

        strokePaint.xfermode = null
        strokePaint.style = Paint.Style.STROKE
        strokePaint.isAntiAlias = true
        strokePaint.shader = shader
        strokePaint.strokeWidth = strokeWidth.toFloat()

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        original = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        shader = BitmapShader(original, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    }

    override fun onDraw(canvas: Canvas) {
        val w = width
        val h = height

        original?.let { bitmap ->
            bitmap.eraseColor(Color.TRANSPARENT) //clear bitmap
            originalCanvas.setBitmap(bitmap)
        }

        super.onDraw(originalCanvas)

        _paint.shader = shader
        _paint.isAntiAlias = true

        canvas.drawCircle((w / 2).toFloat(), (h / 2).toFloat(), (w / 2).toFloat(), _paint)
        if (enableBorder && strokeWidth > 0) {
            canvas.drawCircle((w / 2).toFloat(), (h / 2).toFloat(), (w / 2 - strokeWidth / 2).toFloat(), strokePaint)
        }
    }
}