package com.example.cafeasahi.view.ui.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.cafeasahi.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Suppress("DEPRECATION")
class PerfilFragment : Fragment() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_perfil, container, false)
        firebaseAuth= Firebase.auth

        val btmcamara=view.findViewById<Button>(R.id.btmcamara)
        val pernombre=view.findViewById<TextView>(R.id.pernombre)
        val perdireccion=view.findViewById<TextView>(R.id.perdireccion)
        val pertelefono=view.findViewById<TextView>(R.id.pertelefono)
        val percedula=view.findViewById<TextView>(R.id.percedula)
        val bteditar=view.findViewById<Button>(R.id.bteditar)
        val btactualizar=view.findViewById<Button>(R.id.btmactualizar)

        pernombre.isEnabled=false
        perdireccion.isEnabled=false
        pertelefono.isEnabled=false
        percedula.isEnabled=false


        bteditar.setOnClickListener {
            if(pernombre.isEnabled==false){
                pernombre.isEnabled=true
                perdireccion.isEnabled=true
                pertelefono.isEnabled=true
                percedula.isEnabled=true
            }else if (pernombre.isEnabled==true){
                pernombre.isEnabled=false
                perdireccion.isEnabled=false
                pertelefono.isEnabled=false
                percedula.isEnabled=false
            }
        }


        btactualizar.setOnClickListener {  }

        btmcamara.setOnClickListener {
            val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)
        }

        val btmgalery=view.findViewById<Button>(R.id.btmgalery)
        btmgalery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent,456)
        }


        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView = view?.findViewById<ImageView>(R.id.fotoper)
        if (requestCode == 123) {
            var bitmap = data?.extras?.get("data") as Bitmap
            imageView?.setImageBitmap(bitmap)
        }else if (requestCode==456){
            imageView?.setImageURI(data?.data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_navigation_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        return when(item.itemId){
            R.id.home->{
                findNavController().navigate(R.id.action_perfilFragment_to_homeFragment)
                true
            }
            R.id.ayuda->{
                findNavController().navigate(R.id.action_perfilFragment_to_ayudaFragment)
                true
            }
            R.id.Map->{
                findNavController().navigate(R.id.action_perfilFragment_to_mapaFragment)
                true
            }

            R.id.compras->{
                findNavController().navigate(R.id.action_perfilFragment_to_comprasFragment)
                true
            }
            R.id.cafes->{
                findNavController().navigate(R.id.action_perfilFragment_to_producFragment)
                true
            }
            R.id.cerrar->{
                firebaseAuth.signOut()
                findNavController().navigate(R.id.action_perfilFragment_to_loginActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn =view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btn.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home -> findNavController().navigate(R.id.action_perfilFragment_to_homeFragment)
                R.id.carrito -> findNavController().navigate(R.id.action_perfilFragment_to_comprasFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_perfilFragment_to_mapaFragment)
                R.id.product -> findNavController().navigate(R.id.action_perfilFragment_to_producFragment)
                R.id.help2 -> findNavController().navigate(R.id.action_perfilFragment_to_ayudaFragment)
            }
        }
    }

}
