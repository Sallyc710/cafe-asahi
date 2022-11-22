package com.example.cafeasahi.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cafeasahi.model.cafes
import com.example.cafeasahi.model.compras
import com.google.firebase.firestore.FirebaseFirestore

class repo {
    fun getComprasData():LiveData<MutableList<cafes>>{
        val mutableData=MutableLiveData<MutableList<cafes>>()
        FirebaseFirestore.getInstance().collection("cafes").get()
            .addOnSuccessListener{ result->
                val listaData= mutableListOf<cafes>()
                for(document in result){
                    val titulo=document.getString("titulo")
                    val precio=document.getString("precio")
                    val image=document.getString("image")
                    val cafe=cafes(titulo!!,precio!!,image!!)
                    listaData.add(cafe)
                }

                mutableData.value=listaData
        }
        return mutableData
    }


    fun getCompraData():LiveData<MutableList<compras>>{
        val mutableData=MutableLiveData<MutableList<compras>>()
        FirebaseFirestore.getInstance().collection("compras").get()
            .addOnSuccessListener{ result->
                val listaData= mutableListOf<compras>()
                for(document in result){
                    val titulo=document.getString("titulo")
                    val precio=document.getString("precio")
                    val image=document.getString("image")
                    val compra= compras(titulo!!,precio!!,image!!)
                    listaData.add(compra)
                }

                mutableData.value=listaData
            }
        return mutableData
    }


}