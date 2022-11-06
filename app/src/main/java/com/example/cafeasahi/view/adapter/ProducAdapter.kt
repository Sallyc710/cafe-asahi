package com.example.cafeasahi.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.cafeasahi.R

class ProducAdapter:RecyclerView.Adapter<ProducAdapter.ViewHolder>() {

  override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int):ViewHolder{
      val v=LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_produc,
          viewGroup, false)
      return ViewHolder(v)
  }
    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemprecio: TextView

        init {
            itemImage=ItemView.findViewById(R.id.image)
            itemTitle=ItemView.findViewById(R.id.title)
            itemprecio=ItemView.findViewById(R.id.precio)

        }
    }
    val titles= arrayOf( "Mompos","Bahareque", "Cafi Costa", "Comuneros", "Pueblo","Cafe Jordan","Cafe Miraza","Cafe Quindio" )
    val precio= arrayOf( "$17.000","$18.000", "$16.500", "$19.000", "$97.000","$20.000","$19.500","$18.500" )
    val image= arrayOf( R.drawable.mompo,R.drawable.bahareque,R.drawable.caficosta,R.drawable.comuneros,R.drawable.pueblo,R.drawable.jordan,R.drawable.miraza,R.drawable.quindio )


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text=titles[i]
        viewHolder.itemprecio.text=precio[i]
        viewHolder.itemImage.setImageResource(image[i])

    }

    override fun getItemCount(): Int {
        return titles.size
    }
}