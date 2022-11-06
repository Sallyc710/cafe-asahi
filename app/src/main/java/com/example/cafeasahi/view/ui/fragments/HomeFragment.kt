package com.example.cafeasahi.view.ui.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.cafeasahi.R


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardCaf = view.findViewById<ImageView>(R.id.cardCafe)
            cardCaf.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_producFragment)
            }

        val cardCar = view.findViewById<ImageView>(R.id.cardCarro)
            cardCar.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_compraFragment)
            }

        val cardPe = view.findViewById<ImageView>(R.id.cardPer)
        cardPe.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_perfilFragment)
            }

        val cardMa = view.findViewById<ImageView>(R.id.cardMap)
        cardMa.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mapaFragment)
            }

        val cardHel = view.findViewById<ImageView>(R.id.cardHelp)
        cardHel.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_ayudaFragment)
        }

        val cardFav = view.findViewById<ImageView>(R.id.cardFavo)
        cardFav.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoritosFragment)
        }
    }


}