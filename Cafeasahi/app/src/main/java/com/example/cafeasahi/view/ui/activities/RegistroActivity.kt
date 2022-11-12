package com.example.cafeasahi.view.ui.activities

import android.annotation.SuppressLint
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

class RegistroActivity : AppCompatActivity() {
   lateinit var buttonregistro:Button
   private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        firebaseAuth= Firebase.auth
        buttonregistro=findViewById(R.id.button)
        val correo=findViewById<EditText>(R.id.correoelectronico)
        val constrasena=findViewById<EditText>(R.id.contrasenaregistro)
        buttonregistro.setOnClickListener {
            crearcuenta(correo.text.toString(),constrasena.text.toString())
        }
    }

    private fun crearcuenta(correo:String, contrasena:String){
        firebaseAuth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this){
                Task->if (Task.isSuccessful) {
                    Toast.makeText(baseContext, "Cuenta Creada", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                }else{
                    Toast.makeText(baseContext, "Error creacion", Toast.LENGTH_SHORT).show()
            }

            }
    }
}