package com.example.miniproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.BufferedReader
import java.io.IOException
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.*

class NextPage : AppCompatActivity() {
    //info.txt where saved data
    private val filename = "info.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_page)

        //declaration variables
        val submitBtn = findViewById<Button>(R.id.submitBtn)
        val name = findViewById<EditText>(R.id.nameInput)
        val yearOB = findViewById<EditText>(R.id.dateInput)
        val output = findViewById<TextView>(R.id.output)

        //submit button that receive data and load data from info.txt
        submitBtn.setOnClickListener {
            val age = yearOB.text.toString()
            val text = name.text.toString()

            if (text.isNotBlank() && age.isNotBlank()) {
                val calcAge = 2026 - age.toInt()

                saveToFile(text, calcAge)

                name.text.clear()
                yearOB.text.clear()
                output.text = loadFromFile()

                val input = loadFromFile()
                output.text = input.ifEmpty { "No saved data" }
            } else {
                yearOB.error = "Please fill in fields"
            }
        }
    }

    private fun saveToFile(text: String, age: Int) {
        try {
            val fileOutputStream = openFileOutput(filename, MODE_APPEND)
            val writer = BufferedWriter(OutputStreamWriter(fileOutputStream))

            writer.append("Name: $text, Age: $age")
            writer.newLine()
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun loadFromFile(): String {
        val file = File(filesDir, filename)
        if (!file.exists()) {
            return ""
        }

        val stringBuilder = StringBuilder()
        try {
            val fileInputStream = openFileInput(filename)
            val reader = BufferedReader(InputStreamReader(fileInputStream))
            var line = reader.readLine()

            while (line != null) {
                stringBuilder.append(line).append("\n")
                line = reader.readLine()
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return "Can't read file"
        }
        return stringBuilder.toString()
    }
}