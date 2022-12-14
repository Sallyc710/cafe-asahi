package com.example.cafeasahi.view.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cafeasahi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var iniciobutton:Button
    lateinit var registrobutton: Button
    lateinit var recuperar:TextView
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var recuperarbutton: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth= Firebase.auth

        iniciobutton=findViewById(R.id.Binicio)
        registrobutton=findViewById(R.id.BRegistro)
        recuperar=findViewById(R.id.recuperarB)
        val correo:EditText = findViewById(R.id.correologin)
        val contrasena=findViewById<EditText>(R.id.contrasenalogin)
        recuperarbutton=findViewById(R.id.recuperarB)

        iniciobutton.setOnClickListener{
            login(correo.text.toString(),contrasena.text.toString())
        }

        registrobutton.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        recuperar.setOnClickListener {
            startActivity(Intent(this, RecuperarActivity::class.java))
        }
    }
    private fun login(correo:String, contrasena:String){
        firebaseAuth.signInWithEmailAndPassword(correo,contrasena)
            .addOnCompleteListener(this){
                task->if (task.isSuccessful){
                val user = firebaseAuth.currentUser
                Toast.makeText(baseContext, user?.uid.toString(), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
            }else{
                Toast.makeText(baseContext, "Error Datos", Toast.LENGTH_SHORT).show()
            }
            }
    }
}