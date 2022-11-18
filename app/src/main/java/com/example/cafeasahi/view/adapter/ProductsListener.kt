package com.example.cafeasahi.view.adapter

import com.example.cafeasahi.model.Products

interface ProductsListener {
    fun OnProductsClick(products: Products, position: Int)
}