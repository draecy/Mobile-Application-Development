package com.example.mycontact

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result->
        if(result.resultCode==RESULT_OK){
            val updatedName = result.data?.getStringExtra("updated_name")
            val etName = findViewById<EditText>(R.id.etName)
            etName.setText(updatedName)
        }}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnNext = findViewById<Button>(R.id.btnNext)
        btnNext.setOnClickListener {
            val etName = findViewById<EditText>(R.id.etName)
            val name = etName.text.toString()
            val intent = Intent(this, NextPage::class.java)
            intent.putExtra("student_name", name)
            launcher.launch(intent)
        }
    }
}