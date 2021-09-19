package com.example.roomdao.fragments.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.roomdao.databinding.FragmentMapsBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
class MapsFragment : Fragment() {

//    private lateinit var locationManager: LocationManager
    private val REQUEST_LOCATION_PERMISSION:Int =1
    private lateinit var mGoogleMap: GoogleMap
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        setHasOptionsMenu(true)
    }

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
            mGoogleMap =googleMap
            enableMyLocation()
            mGoogleMap.uiSettings.isMyLocationButtonEnabled = true
            mGoogleMap.isMyLocationEnabled = true
            /**
             * Manipulates the map once available.
             * This callback is triggered when the map is ready to be used.
             * This is where we can add markers or lines, add listeners or move the camera.
             * In this case, we just add a marker near Sydney, Australia.
             * If Google Play services is not installed on the device, the user will be prompted to
             * install it inside the SupportMapFragment. This method will only be triggered once the
             * user has installed Google Play services and returned to the app.
             */
            val hyderabad = LatLng(17.3576, 78.5538)
            googleMap.addMarker(MarkerOptions().position(hyderabad).title("Marker in Hyderabad India"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(hyderabad))
    }

//    private fun enableMyLocation1() {
//        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//            buildAlertMessageNoGps()
//        }
//    }

//    private fun buildAlertMessageNoGps() {
//        MaterialAlertDialogBuilder(requireContext())
//            .setTitle("Location Access")
//            .setIcon(R.drawable.ic_location_on_24)
//            .setMessage("Requesting Location Permission")
//            .setNeutralButton("cancel") { dialog, which ->
//                // Respond to neutral button press
//            }
//            .setNegativeButton("decline") { dialog, which ->
//                // Respond to negative button press
//            }
//            .setPositiveButton("Access") { dialog, which ->
//                val enableBtIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                startActivityForResult(enableBtIntent,REQUEST_LOCATION_PERMISSION)
//
//            }
//            .show()
//        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
//        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//            enableMyLocation1()
//        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(binding.map.id) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }


    override fun onResume() {
        super.onResume()
        Log.i("MapsFragment","onResume")
//        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//            enableMyLocation1()
//        }else return

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun isPermissionGranted() : Boolean {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }


    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            mGoogleMap.isMyLocationEnabled = true
        }
        else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }








}