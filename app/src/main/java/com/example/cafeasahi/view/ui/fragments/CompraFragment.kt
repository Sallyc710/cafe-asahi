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


class CompraFragment : Fragment()  {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_compra, container, false)
        firebaseAuth=Firebase.auth
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_navigation_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        return when(item.itemId){
            R.id.home->{
                findNavController().navigate(R.id.action_compraFragment_to_homeFragment)
                true
            }
            R.id.perfil->{
                findNavController().navigate(R.id.action_compraFragment_to_perfilFragment)
                true
            }
            R.id.Map->{
                findNavController().navigate(R.id.action_compraFragment_to_mapaFragment)
                true
            }

            R.id.ayuda->{
                findNavController().navigate(R.id.action_compraFragment_to_ayudaFragment)
                true
            }
            R.id.cafes->{
                findNavController().navigate(R.id.action_compraFragment_to_producFragment)
                true
            }

            R.id.cerrar->{
                firebaseAuth.signOut()
                findNavController().navigate(R.id.action_compraFragment_to_loginActivity)
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
                R.id.home -> findNavController().navigate(R.id.action_compraFragment_to_homeFragment)
                R.id.help2 -> findNavController().navigate(R.id.action_compraFragment_to_ayudaFragment)
                R.id.product -> findNavController().navigate(R.id.action_compraFragment_to_producFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_compraFragment_to_mapaFragment)
            }
        }
    }

}