package com.example.cafeasahi.view.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cafeasahi.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Suppress("DEPRECATION")
class AyudaFragment : Fragment() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_ayuda, container, false)
        firebaseAuth= Firebase.auth
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_navigation_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        return when(item.itemId){
            R.id.home->{
                findNavController().navigate(R.id.action_ayudaFragment_to_homeFragment)
                true
            }
            R.id.perfil->{
                findNavController().navigate(R.id.action_ayudaFragment_to_perfilFragment)
                true
            }
            R.id.Map->{
                findNavController().navigate(R.id.action_ayudaFragment_to_mapaFragment)
                true
            }

            R.id.compras->{
                findNavController().navigate(R.id.action_ayudaFragment_to_compraFragment)
                true
            }
            R.id.cafes->{
                findNavController().navigate(R.id.action_ayudaFragment_to_producFragment)
                true
            }

            R.id.cerrar->{
                firebaseAuth.signOut()
                findNavController().navigate(R.id.action_ayudaFragment_to_loginActivity)
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
                R.id.home -> findNavController().navigate(R.id.action_ayudaFragment_to_homeFragment)
                R.id.carrito -> findNavController().navigate(R.id.action_ayudaFragment_to_compraFragment)

                R.id.product -> findNavController().navigate(R.id.action_ayudaFragment_to_producFragment)

            }
        }
    }

}