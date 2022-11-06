package com.example.cafeasahi.view.ui.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.cafeasahi.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_perfil, container, false)
        val btmcamara=view.findViewById<Button>(R.id.btmcamara)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn =view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btn.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home -> findNavController().navigate(R.id.action_perfilFragment_to_homeFragment)
                R.id.carrito -> findNavController().navigate(R.id.action_perfilFragment_to_compraFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_perfilFragment_to_mapaFragment)
                R.id.product -> findNavController().navigate(R.id.action_perfilFragment_to_producFragment)
                R.id.help2 -> findNavController().navigate(R.id.action_perfilFragment_to_ayudaFragment)
            }
        }
    }


    }
