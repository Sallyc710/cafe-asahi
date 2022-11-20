package com.example.cafeasahi.view.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Suppress("DEPRECATION")
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
        firebaseAuth= Firebase.auth

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_navigation_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        return when(item.itemId){
            R.id.home->{
                findNavController().navigate(R.id.action_producFragment_to_homeFragment)
                true
            }
            R.id.perfil->{
                findNavController().navigate(R.id.action_producFragment_to_perfilFragment)
                true
            }
            R.id.Map->{
                findNavController().navigate(R.id.action_producFragment_to_mapaFragment)
                true
            }
            R.id.deseos->{
                findNavController().navigate(R.id.action_producFragment_to_favoritosFragment)
                true
            }
            R.id.compras->{
                findNavController().navigate(R.id.action_producFragment_to_compraFragment)
                true
            }
            R.id.ayuda->{
                findNavController().navigate(R.id.action_producFragment_to_ayudaFragment)
                true
            }

            R.id.cerrar->{
                firebaseAuth.signOut()
                findNavController().navigate(R.id.action_producFragment_to_loginActivity)
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
                R.id.home -> findNavController().navigate(R.id.action_producFragment_to_homeFragment)
                R.id.perfil -> findNavController().navigate(R.id.action_producFragment_to_perfilFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_producFragment_to_mapaFragment)
                R.id.carrito -> findNavController().navigate(R.id.action_producFragment_to_compraFragment)
                R.id.favorit -> findNavController().navigate(R.id.action_producFragment_to_favoritosFragment)
            }
        }
    }

}