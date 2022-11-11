package com.example.cafeasahi.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cafeasahi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RecuperarActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var recuperarButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar)
        firebaseAuth= Firebase.auth
        recuperarButton=findViewById(R.id.recuperarB)
        val correo=findViewById<EditText>(R.id.correorecuperar)
        recuperarButton.setOnClickListener {
            cambiocontrasena(correo.text.toString())
        }

    }
    private fun cambiocontrasena(correo:String) {
        firebaseAuth.sendPasswordResetEmail(correo)
            .addOnCompleteListener(this) {
                    task->if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext,
                        "Correo de cambio de constraseña enviado",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }else{
                    Toast.makeText(baseContext, "Error, correo no existe", Toast.LENGTH_SHORT).show()
                }
            }
    }
}