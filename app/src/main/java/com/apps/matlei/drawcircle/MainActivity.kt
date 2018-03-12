package com.apps.matlei.drawcircle

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import java.lang.Float.parseFloat

class MainActivity : AppCompatActivity() {

    // Notes: There is no ternary operator in kotlin, use if like this: val max = if (a > b) a else b.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtRadius = findViewById<EditText>(R.id.activity_main_edittext_radius)
        val imgCircle = findViewById<ImageView>(R.id.activity_main_imageview_circle)

        val btnDrawCircle = findViewById<Button>(R.id.activity_main_button_drawcircle)
        btnDrawCircle.setOnClickListener {

            var numeric = true

            // get radius from edittext
            val radiusString = edtRadius.text.toString()

            // get radius as Float
            var radius = 0f
            try {
                radius = parseFloat(radiusString)
            }
            catch (e: NumberFormatException)
            {
                numeric = false
            }
//                    if (!edtRadius.text.toString().isEmpty() && edtRadius.toString().toFloat() != Float.NaN) edtRadius.text.toString().toFloat() else 123f

            if (numeric && radius > 0f) {
                Toast.makeText(this@MainActivity, "Drawing Circle", Toast.LENGTH_SHORT).show()

                // move softkeyboard out of the screen --> so circle is visible
                val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.SHOW_FORCED)

                // set style and color of circle <=> set Paint
                val paint = Paint()
                paint.color = Color.BLUE
                paint.style = Paint.Style.FILL

                // to store the image -> bitmap object
                val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)

                // create Canvas => to draw on the bitmap
                val canvas = Canvas(bitmap)
                canvas.drawCircle(bitmap.width / 2f, bitmap.height / 2f, radius, paint)

                // output circle on screen
                imgCircle.setImageBitmap(bitmap)
            } else {
                Toast.makeText(this@MainActivity, "Enter a valid radius", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


}
