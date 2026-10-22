package com.example.mycontact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import android.R.attr.name
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NextPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_next_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name = intent.getStringExtra("student_name")

        val welcome = findViewById<TextView>(R.id.welcome)
        welcome.text = "WELCOME, $name"

        val btnReturn = findViewById<Button>(R.id.btnReturn)
        btnReturn.setOnClickListener {
            val resultIntent = Intent()
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        val btnsms = findViewById<Button>(R.id.btnMsg)
        btnsms.setOnClickListener {
            val phoneNumber = "0123456789"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data= Uri.parse("sms:$phoneNumber")
            intent.putExtra("sms_body", "Hello from StudentContactApp!")
            startActivity(intent)
        }

        val btnCall = findViewById<Button>(R.id.btnCall)
        btnCall.setOnClickListener{
            openCall()
        }
    }
    fun openCall(){
        val phoneNumber="0123456789"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data= Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }
}