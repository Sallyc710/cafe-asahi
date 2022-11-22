package com.example.cafeasahi.view.ui.fragments


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.google.firebase.firestore.FirebaseFirestore


class ComprasFragment : Fragment(), OnCompraItemClickListener{
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
        viewModel.fetchCompraData().observe(viewLifecycleOwner, Observer {
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
            findNavController().navigate(R.id.action_compraFragment_to_homeFragment)
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }
    override fun onItemclick(cafe: compras, position: Int) {
        database.collection("compras")
            .document(cafe.titulo)
            .delete()
    }


}