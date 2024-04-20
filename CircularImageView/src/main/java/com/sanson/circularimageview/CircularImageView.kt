package com.sanson.circularimageview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView

class CircularImageView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

    override fun onDraw(canvas: Canvas) {
        val bitmap = drawableToBitmap(drawable)
        val paint = Paint()
        val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader

        val radius = Math.min(width / 2.0, height / 2.0)
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius.toFloat(), paint)
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }
}