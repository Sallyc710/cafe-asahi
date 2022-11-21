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
class FavoritosFragment : Fragment() {
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_favoritos, container, false)
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
                findNavController().navigate(R.id.action_favoritosFragment_to_homeFragment)
                true
            }
            R.id.ayuda->{
                findNavController().navigate(R.id.action_favoritosFragment_to_ayudaFragment)
                true
            }
            R.id.Map->{
                findNavController().navigate(R.id.action_favoritosFragment_to_mapaFragment)
                true
            }
            R.id.perfil->{
                findNavController().navigate(R.id.action_favoritosFragment_to_perfilFragment)
                true
            }
            R.id.compras->{
                findNavController().navigate(R.id.action_favoritosFragment_to_comprasFragment)
                true
            }
            R.id.cafes->{
                findNavController().navigate(R.id.action_favoritosFragment_to_producFragment)
                true
            }
            R.id.cerrar->{
                firebaseAuth.signOut()
                findNavController().navigate(R.id.action_favoritosFragment_to_loginActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

}