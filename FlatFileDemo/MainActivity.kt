package com.example.flatfiledemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    private lateinit var etInput: EditText
    private lateinit var btnSave: Button
    private lateinit var btnLoad: Button
    private lateinit var tvContents: TextView

    private val filename = "user_data.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etInput = findViewById(R.id.etInput)
        btnSave = findViewById(R.id.btnSave)
        btnLoad = findViewById(R.id.btnLoad)
        tvContents = findViewById(R.id.tvContents)

        btnSave.setOnClickListener {
            val text = etInput.text.toString()

            if (text.isNotBlank()) {
                saveToFile(text)
                etInput.text.clear()
                tvContents.text = "Data disimpan ke fail."
            } else {
                etInput.error = "Sila masukkan teks dahulu"
            }
        }

        btnLoad.setOnClickListener {
            val content = loadFromFile()

            tvContents.text = if (content.isNotEmpty()) content else "Tiada data dalam fail."
        }
    }

    private fun saveToFile(text: String) {
        try {
            val fileOutputStream = openFileOutput(filename, MODE_APPEND)
            val writer = BufferedWriter(OutputStreamWriter(fileOutputStream))
            writer.append(text)
            writer.newLine()
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
            tvContents.text = "Ralat simpan fail: ${e.localizedMessage}"
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
            return "Ralat baca fail: ${e.localizedMessage}"
        }

        return stringBuilder.toString()
    }
}