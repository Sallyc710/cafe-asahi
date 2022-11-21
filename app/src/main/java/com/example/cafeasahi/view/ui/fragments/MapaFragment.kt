package com.example.cafeasahi.view.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cafeasahi.R
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Suppress("DEPRECATION")
class MapaFragment : Fragment(), OnMapReadyCallback {
    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mapa, container, false)
        firebaseAuth= Firebase.auth
        val mapFragment =
            this.childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    override fun onMapReady(map: GoogleMap) {
        val colombia = com.google.android.gms.maps.model.LatLng(4.570868, -74.297333)
        map?.let {
            this.googleMap = it
            map.addMarker(MarkerOptions().position(colombia))
        }
        enableLocation()

    }

    private fun islocationPermissionGrated() = ContextCompat.checkSelfPermission(
        this.requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    private fun enableLocation() {
        if (!::googleMap.isInitialized) return
        if (islocationPermissionGrated()) {
            googleMap.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this.requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Toast.makeText(context, "requiere activar permisos en ajustes", Toast.LENGTH_SHORT)
                .show()
        } else {
            ActivityCompat.requestPermissions(
                this.requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }


    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                googleMap.isMyLocationEnabled = true
            } else {
                Toast.makeText(
                    context,
                    "Para activar la localización ve a ajustes y acepta los permisos",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_navigation_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        return when(item.itemId){
            R.id.home->{
                findNavController().navigate(R.id.action_mapaFragment_to_homeFragment)
                true
            }
            R.id.ayuda->{
                findNavController().navigate(R.id.action_mapaFragment_to_ayudaFragment)
                true
            }
            R.id.perfil->{
                findNavController().navigate(R.id.action_mapaFragment_to_perfilFragment)
                true
            }

            R.id.compras->{
                findNavController().navigate(R.id.action_mapaFragment_to_compraFragment)
                true
            }
            R.id.cafes->{
                findNavController().navigate(R.id.action_mapaFragment_to_producFragment)
                true
            }
            R.id.cerrar->{
                firebaseAuth.signOut()
                findNavController().navigate(R.id.action_mapaFragment_to_loginActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn =view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btn.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home -> findNavController().navigate(R.id.action_mapaFragment_to_homeFragment)
                R.id.carrito -> findNavController().navigate(R.id.action_mapaFragment_to_compraFragment)
                R.id.product -> findNavController().navigate(R.id.action_mapaFragment_to_producFragment)
                R.id.help2 -> findNavController().navigate(R.id.action_mapaFragment_to_ayudaFragment)
            }
        }
    }
}
