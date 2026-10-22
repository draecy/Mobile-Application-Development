package com.example.studentprofileapp

import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.name)
        val idInput = findViewById<EditText>(R.id.id)
        val submitBtn = findViewById<Button>(R.id.submitBtn)
        val outputDisplay = findViewById<TextView>(R.id.output)

        submitBtn.setOnClickListener {
            val studentName = nameInput.text.toString()
            val studentId = idInput.text.toString()

            if (studentName.isNotBlank() && studentId.isNotBlank()) {
                displayStudent()
                outputDisplay.text = "Name: $studentName \nID: $studentId"
                nameInput.text.clear()
                idInput.text.clear()
            } else {
                Toast.makeText(this, "Fields are missing!", Toast.LENGTH_SHORT).show()
                outputDisplay.text = "Please fill all the fields"
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun displayStudent() {
        Toast.makeText(this, "PROFILE SUBMITTED", Toast.LENGTH_SHORT).show()
    }
}