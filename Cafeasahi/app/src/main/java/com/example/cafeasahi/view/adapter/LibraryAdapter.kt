package com.example.cafeasahi.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.cafeasahi.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class LibraryAdapter: RecyclerView.Adapter<LibraryAdapter.ViewHolder>(){
        override fun onCreateViewHolder(viewGroup: ViewGroup, i:Int): ViewHolder{
        val v=LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_librery, viewGroup, false)
            return ViewHolder(v)

    }
        inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
            var itemImage: ImageView
            var itemTitle: TextView
            var itemPrecio: TextView


            init {
                itemImage=ItemView.findViewById(R.id.image)
                itemTitle=ItemView.findViewById(R.id.title)
                itemPrecio=ItemView.findViewById(R.id.Precio)

            }

        }

        val titles= arrayOf("Pueblo 57", "Bahareque origen el Águila", "Monte Brujas Kavas", "Comuneros", "Valle de Umbra", "Mi raza", "El Jordán", "La mompo")
        val precio= arrayOf("(COP) $23000", "(COP) $24300", "(COP) $28000 y $45000 / $45000  ", "(COP) $36000 ", "(COP) $38900 / $27999", "(COP) $26500 / $24500 / $32500", "(COP) $23500", "(COP) $30000")
        val image= arrayOf(R.drawable.pueblo57, R.drawable.bahareque2, R.drawable.comuneros3, R.drawable.valledeumbra4, R.drawable.miraza5, R.drawable.cafeeljordan6, R.drawable.lamompo7)
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text=titles[i]
        viewHolder.itemPrecio.text=precio[i]

        viewHolder.itemImage.setImageResource(image[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}