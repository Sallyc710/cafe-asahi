package com.example.cafeasahi.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cafeasahi.model.cafes
import com.google.firebase.firestore.FirebaseFirestore

class repo {
    fun getlibraryData():LiveData<MutableList<cafes>>{
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
}