package com.example.cafeasahi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cafeasahi.model.detalle
import com.example.cafeasahi.repositorio.repo2

class DetalleViewModel: ViewModel() {
    val repo2= repo2()
    fun librarydata(): LiveData<MutableList<detalle>> {
        val mutabledata= MutableLiveData<MutableList<detalle>>()
        repo2.getlibraryData().observeForever {result->
            mutabledata.value= result
        }
        return mutabledata
    }
}