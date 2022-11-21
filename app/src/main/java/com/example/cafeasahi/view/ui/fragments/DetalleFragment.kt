package com.example.cafeasahi.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cafeasahi.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class DetalleFragment : Fragment() {


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_detalle, container, false)


	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val btn =view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
		btn.setOnNavigationItemReselectedListener {
			when(it.itemId){
				R.id.home -> findNavController().navigate(R.id.action_detalleFragment_to_homeFragment)
				R.id.carrito -> findNavController().navigate(R.id.action_detalleFragment_to_comprasFragment)
				R.id.product -> findNavController().navigate(R.id.action_detalleFragment_to_producFragment)
				R.id.help2 -> findNavController().navigate(R.id.action_detalleFragment_to_ayudaFragment)
				R.id.mapa -> findNavController().navigate(R.id.action_detalleFragment_to_mapaFragment)

			}
		}
	}

}