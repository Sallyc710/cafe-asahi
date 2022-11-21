package com.example.cafeasahi.view.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeasahi.R
import com.example.cafeasahi.model.compras
import com.example.cafeasahi.view.adapter.ComprasAdapter
import com.example.cafeasahi.view.adapter.OnCompraItemClickListener
import com.example.cafeasahi.viewmodel.ComprasViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase



class ComprasFragment : Fragment(), OnCompraItemClickListener{
    lateinit var firebaseAuth:FirebaseAuth
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ComprasAdapter
    lateinit var precioT:TextView
    lateinit var compraT:TextView
    val database:FirebaseFirestore= FirebaseFirestore.getInstance()
    private val viewModel by lazy { ViewModelProvider(this).get(ComprasViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_compras, container, false)
        firebaseAuth=Firebase.auth
        recyclerView=view.findViewById(R.id.recyclerviewcompra)
        precioT=view.findViewById(R.id.preciototal)
        compraT=view.findViewById(R.id.realizar)
        adapter= ComprasAdapter(requireContext(),this)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=adapter
        observeData()
        preciototal()
       compraT.setOnClickListener {
           realizarcompra()
       }
        return view
    }

    private fun observeData() {
        viewModel.fetchComprasData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
    private fun preciototal(){
        database.collection("compras")
            .get()
            .addOnSuccessListener {
                result->
                val preciounitario= mutableListOf<String>()
                for (document in result){
                    val precio=document["precio"].toString()
                    preciounitario.add(precio!!)
                }
                val preciototal=preciounitario.mapNotNull { it.toIntOrNull()}.sum()
                precioT.setText(Integer.toString(preciototal))
            }
    }
    private fun realizarcompra(){
        val builder=AlertDialog.Builder(requireContext())
        builder.setTitle("compracafeasahi")
        builder.setMessage("¿Desea realizar esta compra?")
        builder.setPositiveButton("Aceptar"){
            dialog,which->
            findNavController().navigate(R.id.action_comprasFragment_to_homeFragment)
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }
    override fun onItemclick(cafe: compras, position: Int) {
        database.collection("compras")
            .document(cafe.titulo)
            .delete()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_navigation_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        return when(item.itemId){
            R.id.home->{
                findNavController().navigate(R.id.action_comprasFragment_to_homeFragment)
                true
            }
            R.id.perfil->{
                findNavController().navigate(R.id.action_comprasFragment_to_perfilFragment)
                true
            }
            R.id.Map->{
                findNavController().navigate(R.id.action_comprasFragment_to_mapaFragment)
                true
            }

            R.id.ayuda->{
                findNavController().navigate(R.id.action_comprasFragment_to_ayudaFragment)
                true
            }
            R.id.cafes->{
                findNavController().navigate(R.id.action_comprasFragment_to_producFragment)
                true
            }

            R.id.cerrar->{
                firebaseAuth.signOut()
                findNavController().navigate(R.id.action_comprasFragment_to_loginActivity)
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
                R.id.home -> findNavController().navigate(R.id.action_comprasFragment_to_homeFragment)
                R.id.help2 -> findNavController().navigate(R.id.action_comprasFragment_to_ayudaFragment)
                R.id.product -> findNavController().navigate(R.id.action_comprasFragment_to_producFragment)
                R.id.mapa -> findNavController().navigate(R.id.action_comprasFragment_to_mapaFragment)
            }
        }
    }


}