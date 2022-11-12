package com.example.cafeasahi.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.cafeasahi.R
import kotlinx.coroutines.supervisorScope


class fragment_menu : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardLib=view.findViewById<ImageView>(R.id.carcafes)
        cardLib.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_menu_to_cafesFragment)
        }
        val cardcompra=view.findViewById<ImageView>(R.id.carritocompras)
        cardcompra.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_menu_to_comprasFragment)
        }
        val cardperfil=view.findViewById<ImageView>(R.id.perfil)
        cardperfil.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_menu_to_perfilFragment)
        }
        val cardmapa=view.findViewById<ImageView>(R.id.mapa)
        cardmapa.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_menu_to_mapaFragment)
        }
        val cardayuda=view.findViewById<ImageView>(R.id.ayuda)
        cardayuda.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_menu_to_ayudaFragment)
        }
        val carddeseos=view.findViewById<ImageView>(R.id.deseos)
        carddeseos.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_menu_to_deseosFragment)
        }
        }
    }


