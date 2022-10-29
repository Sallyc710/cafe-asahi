package com.example.cafeasahi.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.cafeasahi.R

class LoginActivity : AppCompatActivity() {
    lateinit var BRegistro:Button
    lateinit var Binicio: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Binicio=findViewById(R.id.Binicio)
        BRegistro=findViewById(R.id.BRegistro)
        BRegistro.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}