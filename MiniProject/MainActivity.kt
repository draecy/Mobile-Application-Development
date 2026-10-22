package com.example.miniproject

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //declaration variables
        val zoomIn = findViewById<Button>(R.id.zoomIn)
        val zoomOut = findViewById<Button>(R.id.zoomOut)
        val fadeIn = findViewById<Button>(R.id.fadeIn)
        val fadeOut = findViewById<Button>(R.id.fadeOut)
        val rotate = findViewById<Button>(R.id.rotate)
        val nextPage = findViewById<Button>(R.id.nextBtn)
        val callBtn = findViewById<Button>(R.id.callBtn)
        val messageBtn = findViewById<Button>(R.id.messageBtn)
        val insideOut = findViewById<ImageView>(R.id.insideOut)

        //navigation button
        nextPage.setOnClickListener {
            val intent = Intent(this, NextPage::class.java)
            startActivity(intent)
        }

        //zoom in animation
        zoomIn.setOnClickListener {
            insideOut.animate()
                .scaleX(1.5f)
                .scaleY(1.5f)
                .withEndAction {
                    insideOut.animate()
                        .scaleY(1.0f)
                        .scaleX(1.0f)
                }
        }

        //zoom out animation
        zoomOut.setOnClickListener {
            insideOut.animate()
                .scaleY(0.5f)
                .scaleX(0.5f)
                .withEndAction {
                    insideOut.animate()
                        .scaleY(1.0f)
                        .scaleX(1.0f)
                }
        }

        //fade in animation
        fadeIn.setOnClickListener {
            insideOut.alpha = 0f
            insideOut.animate()
                .alpha(1f)
        }

        //fade out animation
        fadeOut.setOnClickListener {
            insideOut.animate()
                .alpha(0f)
        }

        //rotation animation
        rotate.setOnClickListener {
            insideOut.animate().rotation(180f)
        }

        //message button to message number
        messageBtn.setOnClickListener {
            val phoneNumber = "01234567980"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("sms: $phoneNumber")
            intent.putExtra("sms_body", "Hello from Mini Project Hanis!")
            startActivity(intent)
        }

        //call button to call number
        callBtn.setOnClickListener {
            opencall()
        }
    }
    //call function navigate to dialed number
    fun opencall() {
        val phoneNumber = "01234567980"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("tel: $phoneNumber")
        startActivity(intent)
    }
}