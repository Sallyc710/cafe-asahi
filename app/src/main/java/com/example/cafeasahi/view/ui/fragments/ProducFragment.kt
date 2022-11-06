package com.example.cafeasahi.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeasahi.R
import com.example.cafeasahi.view.adapter.ProducAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProducFragment : Fragment() {
    lateinit  var recyclerLib:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_produc, container, false)
        recyclerLib=view.findViewById(R.id.recyclerview)
        val adapter=ProducAdapter()
        recyclerLib.layoutManager=LinearLayoutManager(context)
        recyclerLib.adapter=adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn =view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btn.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home -> findNavController().navigate(R.id.action_producFragment_to_homeFragment)
                R.id.perfil -> findNavController().navigate(R.id.action_producFragment_to_perfilFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_producFragment_to_mapaFragment)
                R.id.carrito -> findNavController().navigate(R.id.action_producFragment_to_compraFragment)
                R.id.favorit -> findNavController().navigate(R.id.action_producFragment_to_favoritosFragment)
            }
        }
    }

}