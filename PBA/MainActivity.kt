package com.example.pba

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var etCarModel: EditText
    lateinit var etBasePrice: EditText
    lateinit var btnCalculate: Button
    lateinit var tvResult: TextView

    lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCarModel = findViewById(R.id.etCarModel)
        etBasePrice = findViewById(R.id.etBasePrice)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)

        db = DBHelper(this)

        btnCalculate.setOnClickListener {

            val model = etCarModel.text.toString()
            val basePrice = etBasePrice.text.toString()

            if (model.isEmpty() || basePrice.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price = basePrice.toDouble()

            // Calculation: Final Price = Base + 10%
            val finalPrice = price + (price * 0.10)

            // Save to database
            db.insertData(model, finalPrice)

            // Show latest data
            tvResult.text = db.getLastData()

            // Data Saved toast
            Toast.makeText(this, "Data Saved Successfully!", Toast.LENGTH_SHORT).show()
        }
    }
}