package com.example.cafeasahi.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeasahi.R
import com.example.cafeasahi.model.detalle
import com.squareup.picasso.Picasso

class DetalleAdapter(private val context: Context):RecyclerView.Adapter<DetalleAdapter.ViewHolder>() {
    private var cafelista=mutableListOf<detalle>()

    fun setListData(data: MutableList<detalle>){
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
        val v= LayoutInflater.from(viewGroup.context).inflate(
            R.layout.card_view_produc,
            viewGroup, false)
        return ViewHolder(v, mListener)
    }


    inner class ViewHolder(ItemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(ItemView){
        fun binWew(cafe: detalle){
            itemView.findViewById<TextView>(R.id.tituloDetalle).text =cafe.titulo
            itemView.findViewById<TextView>(R.id.precio).text=cafe.precio
            Picasso.with(context).load(cafe.image).into(itemView.findViewById<ImageView>(R.id.imaDetalle))

        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }




    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val cafe=cafelista[position]
        viewHolder.binWew(cafe)
    }

    override fun getItemCount(): Int {
        return if(cafelista.size>0){
            cafelista.size
        }else{
            0
        }
    }

}