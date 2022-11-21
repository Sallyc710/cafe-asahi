package com.example.cafeasahi.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cafeasahi.model.compras
import com.google.firebase.firestore.FirebaseFirestore

class repocompras {
    fun getComprasData():LiveData<MutableList<compras>>{
        val mutableData=MutableLiveData<MutableList<compras>>()
        FirebaseFirestore.getInstance().collection("compras").get()
            .addOnSuccessListener{ result->
                val listaData= mutableListOf<compras>()
                for(document in result){
                    val titulo=document.getString("titulo")
                    val precio=document.getString("precio")
                    val image=document.getString("image")
                    val cafe=compras(titulo!!,precio!!,image!!)
                    listaData.add(cafe)
                }

                mutableData.value=listaData
            }
        return mutableData
    }
}