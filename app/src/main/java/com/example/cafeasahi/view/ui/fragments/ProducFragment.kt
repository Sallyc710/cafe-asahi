package com.example.cafeasahi.view.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeasahi.R
import com.example.cafeasahi.view.adapter.ProducAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cafeasahi.model.cafes
import com.example.cafeasahi.view.adapter.OnBookItemClickListener
import com.example.cafeasahi.viewmodel.CafeViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class ProducFragment : Fragment(), OnBookItemClickListener {
    lateinit  var recyclerLib:RecyclerView
    lateinit  var firebaseAuth: FirebaseAuth
    lateinit var adapter: ProducAdapter
    private var cafelista=mutableListOf<cafes>()
    val database:FirebaseFirestore=FirebaseFirestore.getInstance()
    private val  viewmodel by lazy {ViewModelProvider(this).get(CafeViewModel::class.java)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_produc, container, false)
        firebaseAuth=Firebase.auth
        recyclerLib=view.findViewById(R.id.recyclerview)
        adapter=ProducAdapter(requireContext(), this)
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
        viewmodel.Comprasdata().observe(viewLifecycleOwner, Observer{
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
                R.id.carrito -> findNavController().navigate(R.id.action_producFragment_to_compraFragment)
                R.id.help2 -> findNavController().navigate(R.id.action_producFragment_to_ayudaFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_producFragment_to_mapaFragment)
            }
        }
    }

    override fun onItemclick(cafe: cafes, position: Int) {
        val titulo:String=cafe.titulo
        val precio:String=cafe.precio
        val image:String=cafe.image
        val dato= hashMapOf(
            "titulo" to titulo,
            "precio" to precio,
            "image" to image
        )
        database.collection("compras")
            .document(titulo)
            .set(dato)
            .addOnSuccessListener {
                Toast.makeText(context, "el cafe fue añadido al carrito", Toast.LENGTH_SHORT).show()
            }

    }

}