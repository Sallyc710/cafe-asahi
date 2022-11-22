package com.example.cafeasahi.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeasahi.R
import com.example.cafeasahi.model.Products
import com.example.cafeasahi.model.cafes
import com.squareup.picasso.Picasso

class ProducAdapter(private val context : Context,var clickListener:OnBookItemClickListener):RecyclerView.Adapter<ProducAdapter.ViewHolder>() {
    private var cafelista=mutableListOf<cafes>()
    lateinit var pros:Products

    fun setListData(data:MutableList<cafes>){
        cafelista=data
    }

    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

  override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int):ViewHolder{
      val v=LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_produc,
          viewGroup, false)
      return ViewHolder(v, mListener)
  }

    inner class ViewHolder(ItemView: View, listener: onItemClickListener ): RecyclerView.ViewHolder(ItemView){
        fun binWew(cafe: cafes, action:OnBookItemClickListener){
            itemView.findViewById<TextView>(R.id.title).text =cafe.titulo
            itemView.findViewById<TextView>(R.id.precio).text=cafe.precio
            Picasso.with(context).load(cafe.image).into(itemView.findViewById<ImageView>(R.id.image))
            val btncarrito=itemView.findViewById<ImageButton>(R.id.carrito)
            btncarrito.setOnClickListener {
                action.onItemclick(cafe,adapterPosition)
            }
        }

    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val cafe=cafelista[i]
         viewHolder.binWew(cafe,clickListener)
            }

    override fun getItemCount(): Int {
        return if(cafelista.size>0){
            cafelista.size
        }else{
            0
        }
    }
}
interface OnBookItemClickListener{
    fun onItemclick(cafe:cafes,position: Int)
}