package com.example.cafeasahi.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cafeasahi.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onBackPressed() {
        return
    }
}