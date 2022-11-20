package com.example.cafeasahi.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeasahi.R
import com.example.cafeasahi.model.Cafes
import com.squareup.picasso.Picasso

class ProducAdapter(private val context: Context):RecyclerView.Adapter<ProducAdapter.ViewHolder>() {
    private lateinit var mListener: onItemClickListener
    private var cafeslista=mutableListOf<Cafes>()

    fun setListData(data:MutableList<Cafes>){
        cafeslista=data
    }

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


    inner class ViewHolder(ItemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(ItemView){
        fun binWew(cafes: Cafes){
            itemView.findViewById<TextView>(R.id.title).text=cafes.titulo
            itemView.findViewById<TextView>(R.id.precio).text=cafes.precio
            Picasso.with(context).load(cafes.image).into(itemView.findViewById<ImageView>(R.id.image))
        }



        init {

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }



    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val cafe=cafeslista[i]
        viewHolder.binWew(cafe)
            }

    override fun getItemCount(): Int {
        return if(cafeslista.size>0){
            cafeslista.size
        }else{
            0
        }
    }
}