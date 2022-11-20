package com.example.cafeasahi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cafeasahi.model.Cafes
import com.example.cafeasahi.repositorio.repo

class CafesViewModel: ViewModel() {
        val repo= repo()
    fun libraryData():LiveData<MutableList<Cafes>>{
        val mutabledata=MutableLiveData<MutableList<Cafes>>()
        repo.getlibraryData().observeForever {result->
            mutabledata.value=result
        }
        return mutabledata
    }
}