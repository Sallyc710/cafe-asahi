package com.example.cafeasahi.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeasahi.R
import com.example.cafeasahi.R.id.action_fragment_menu_to_cafesFragment
import com.example.cafeasahi.view.adapter.LibraryAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Suppress("DEPRECATION")
class CafesFragment : Fragment() {
    lateinit var recyclerLib: RecyclerView
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cafes, container, false)
        recyclerLib = view.findViewById(R.id.recyclerview)
        val adapter = LibraryAdapter()
        recyclerLib.layoutManager = LinearLayoutManager(context)
        recyclerLib.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm = view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btm.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.Home -> findNavController().navigate(R.id.action_cafesFragment_to_fragment_menu)
                R.id.Perf -> findNavController().navigate(R.id.action_cafesFragment_to_perfilFragment)
                R.id.Map -> findNavController().navigate(R.id.action_cafesFragment_to_mapaFragment)
                R.id.cerrar -> {
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_cafesFragment_to_loginActivity)
                    true
                }
            }

        }

    }
}