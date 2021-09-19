package com.example.roomdao.fragments.list

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdao.R
import com.example.roomdao.databinding.FragmentListBinding
import com.example.roomdao.hidekeyboard.hideKeyBoard
import com.example.roomdao.viewmodel.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager





class ListFragment : Fragment(),SearchView.OnQueryTextListener {

    private lateinit var userViewModel:UserViewModel
    private lateinit var adapter: ListAdapter
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"onCreate")
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Retrieve and inflate the layout for this fragment
        Log.i(TAG,"onCreateView")
        _binding = FragmentListBinding.inflate(inflater, container, false)
        adapter = ListAdapter()
        val recyclerView = binding.listFragmentRecyclerView
        recyclerView.adapter = adapter
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllUserData.observe(viewLifecycleOwner, Observer {
            Log.i(TAG,"readAllUsers")
            adapter.setUserList(it)

        })
        binding.addMemberFab.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToAddFragment())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        hideKeyBoard(requireActivity())

        Log.i(TAG,"onViewCreated")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.i(TAG,"onCreateOptionsMenu")

//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_frament_menu, menu)


        val search =menu.findItem(R.id.searchUser)
        val searchView =search.actionView as SearchView
        searchView.isSubmitButtonEnabled =true
        searchView.queryHint ="Name or Email"
        searchView.setOnQueryTextListener(this)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.i(TAG,"onOptionsItemSelected")

        return when (item.itemId) {
            R.id.listDeleteAll -> {
                deleteAllUser()
                // navigate to settings screen
                true
            }
            R.id.googleMaps -> {
                findNavController().navigate(ListFragmentDirections.actionListFragmentToMapsFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllUser() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete All")
            .setIcon(R.drawable.ic_delete_24)
            .setMessage("Are you sure want to delete All Members ?")
            .setNeutralButton("cancel") { dialog, which ->
                // Respond to neutral button press
            }
            .setNegativeButton("decline") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("delete") { dialog, which ->
                userViewModel.deleteAllUsers()
                Toast.makeText(requireContext(),"Successfully deleted All Members ", Toast.LENGTH_SHORT).show()
            }
            .show()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG,"onDestroyView")

        _binding = null
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i(TAG,"onViewStateRestored")

    }


    override fun onStart() {
        super.onStart()
        Log.i(TAG,"onStart")


    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG,"onStop")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG,"onSaveInstanceState")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"onDestroy")

    }

    companion object{

        const val TAG="ListFragment"
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query!=null){
            searchDataBase(query)
        }
        Log.i(TAG,"onQueryTextSubmit")
        return true
    }
    override fun onQueryTextChange(query: String?): Boolean {
        if(query!=null){
            searchDataBase(query)
        }
        Log.i(TAG,"onQueryTextChange")
        return true
    }

    private fun searchDataBase(query: String) {

        var searchQuery:String =query
        searchQuery ="%$searchQuery%"

        userViewModel.searchDataBase(searchQuery).observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.i(TAG,"searchDataBase")
               adapter.setUserList(it)
            }

        })

    }


//    private fun getLocation() {
//        if (ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            )
//            != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                REQUEST_LOCATION_PERMISSION
//            )
//        } else {
//            Log.d(TAG, "getLocation: permissions granted")
//        }
//    }




}