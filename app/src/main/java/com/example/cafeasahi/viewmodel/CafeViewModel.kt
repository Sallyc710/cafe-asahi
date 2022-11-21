package com.example.cafeasahi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cafeasahi.model.cafes
import com.example.cafeasahi.repositorio.repo

class CafeViewModel: ViewModel() {
    val repo=repo()
    fun Comprasdata(): LiveData<MutableList<cafes>> {
        val mutabledata=MutableLiveData<MutableList<cafes>>()
        repo.getComprasData().observeForever {result->
            mutabledata.value= result
        }
        return mutabledata
    }

}