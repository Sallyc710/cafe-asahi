package com.example.cafeasahi.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cafeasahi.model.Cafes
import com.google.firebase.firestore.FirebaseFirestore

class repo {
    fun getlibraryData(): LiveData<MutableList<Cafes>>{
        val mutabledata=MutableLiveData<MutableList<Cafes>>()
        FirebaseFirestore.getInstance().collection("cafes").get()
            .addOnSuccessListener { result->
                val listData= mutableListOf<Cafes>()
                for (document in result){
                    val titulo=document.getString("titulo")
                    val precio=document.getString("precio")
                    val image=document.getString("image")
                    val detalle=document.getString("detalle")
                    val cafe=Cafes(titulo!!,precio!!,image!!, detalle!! )
                    listData.add(cafe)

                }
                mutabledata.value=listData
            }
        return mutabledata
    }
}