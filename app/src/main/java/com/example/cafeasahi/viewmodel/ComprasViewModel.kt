package com.example.cafeasahi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cafeasahi.model.compras
import com.example.cafeasahi.repositorio.repo

class ComprasViewModel:ViewModel(){
    val repo= repo()
    fun fetchCompraData():LiveData<MutableList<compras>>{
        val mutableData=MutableLiveData<MutableList<compras>>()
        repo.getCompraData().observeForever{
            mutableData.value=it
        }
        return mutableData

    }

}