package com.example.cafeasahi.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cafeasahi.model.detalle
import com.google.firebase.firestore.FirebaseFirestore

class repo2 {
    fun getlibraryData(): LiveData<MutableList<detalle>> {
        val mutableData= MutableLiveData<MutableList<detalle>>()
        FirebaseFirestore.getInstance().collection("cafes").get()
            .addOnSuccessListener{ result->
                val listaData= mutableListOf<detalle>()
                for(document in result){
                    val titulo=document.getString("titulo")
                    val precio=document.getString("precio")
                    val image=document.getString("image")
                    val detall= detalle(titulo!!,precio!!,image!!)
                    listaData.add(detall)
                }

                mutableData.value=listaData
            }
        return mutableData
    }
}