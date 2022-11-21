package com.example.cafeasahi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cafeasahi.model.compras
import com.example.cafeasahi.repositorio.repocompras

class ComprasViewModel:ViewModel(){
    val repocompras=repocompras()
    fun fetchComprasData():LiveData<MutableList<compras>>{
        val mutableData=MutableLiveData<MutableList<compras>>()
        repocompras.getComprasData().observeForever{
            mutableData.value=it
        }
        return mutableData

    }

}