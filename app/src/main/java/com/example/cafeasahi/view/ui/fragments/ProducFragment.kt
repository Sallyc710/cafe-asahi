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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cafeasahi.model.cafes
import com.example.cafeasahi.viewmodel.CafeViewModel
import com.google.firebase.auth.FirebaseAuth



class ProducFragment : Fragment() {
    lateinit  var recyclerLib:RecyclerView
    lateinit  var firebaseAuth: FirebaseAuth
    lateinit var adapter: ProducAdapter
    private var cafelista=mutableListOf<cafes>()
    private val  viewmodel by lazy {ViewModelProvider(this).get(CafeViewModel::class.java)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_produc, container, false)
        recyclerLib=view.findViewById(R.id.recyclerview)
        adapter=ProducAdapter(requireContext())
        recyclerLib.layoutManager=LinearLayoutManager(context)
        recyclerLib.adapter=adapter

        observeData()

        adapter.setOnItemClickListener(object:ProducAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                    findNavController().navigate(R.id.action_producFragment_to_detalleFragment)


            }
        })

        return view
    }

    fun observeData(){
        viewmodel.librarydata().observe(viewLifecycleOwner, Observer{
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
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