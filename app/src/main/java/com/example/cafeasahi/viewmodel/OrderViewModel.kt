package com.example.cafeasahi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cafeasahi.model.Products
import com.example.cafeasahi.network.Callback
import com.example.cafeasahi.network.FirestoreService

class OrderViewModel: ViewModel() {
    val firestoreService= FirestoreService()
    var listProducts : MutableLiveData<List<Products>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()
    fun refresh() {
        getProductsFromFrirebase()
    }

    fun getProductsFromFrirebase(){
        firestoreService.getProducts(object : Callback<List<Products>> {
            override fun onSuccess(result: List<Products>?) {
                listProducts.postValue(result)
                processFinished()
            }

            override fun onfailed(exception: Exception) {
               processFinished()
            }
        })
    }
    fun processFinished(){
        isLoading.value = true
    }
}