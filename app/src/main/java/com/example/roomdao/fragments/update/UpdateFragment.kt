package com.example.roomdao.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdao.R
import com.example.roomdao.databinding.FragmentUpdateBinding
import com.example.roomdao.hidekeyboard.hideKeyBoard
import com.example.roomdao.model.User
import com.example.roomdao.viewmodel.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel:UserViewModel

    private  var _binding:FragmentUpdateBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater,container,false)


        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)



        binding.updateFirstname.setText(args.updateUser.firstName)
        binding.updateLastname.setText(args.updateUser.lastName)
        binding.updateEmail.setText(args.updateUser.email)
        binding.updatePhone.setText(args.updateUser.phone)
        binding.updateAddress.setText(args.updateUser.address)
        binding.updateAge.setText(args.updateUser.age.toString())
        binding.updateTextField.setText(args.updateUser.gender)

        val items = listOf("MALE","FEMALE")
        val adapter = ArrayAdapter(requireContext(), R.layout.gender_layout, items)
        binding.updateTextField.setAdapter(adapter)

        binding.updateUser.setOnClickListener {
            updateUser()


        }

        return binding.root
    }

    private fun updateUser() {
        val updatedUser = User(id =args.updateUser.id, firstName = binding.updateFirstname.text.toString(),
            lastName = binding.updateLastname.text.toString(),
            email = binding.updateEmail.text.toString(),
            phone = binding.updatePhone.text.toString(),
            address = binding.updateAddress.text.toString(),
            age = binding.updateAge.text.toString().toInt(),
            gender = binding.updateTextField.text.toString()
        )

        mUserViewModel.updateUser(updatedUser)
        this.findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToListFragment())
        Toast.makeText(requireContext(),"successfully updated ${updatedUser.firstName}",Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.update_frament_menu, menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.updateDelete -> {
                deleteUser()
                // navigate to settings screen
                true
            }
//            R.id.action_done -> {
//                // save profile changes
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteUser() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete")
            .setIcon(R.drawable.ic_delete_24)
            .setMessage("Are you sure want to delete ?")
            .setNeutralButton("cancel") { dialog, which ->
                // Respond to neutral button press
            }
            .setNegativeButton("decline") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("delete") { dialog, which ->
                mUserViewModel.deleteUser(args.updateUser)
                Toast.makeText(requireContext(),"Successfully deleted ${args.updateUser.firstName}",Toast.LENGTH_SHORT).show()
                findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToListFragment())
            }
            .show()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        hideKeyBoard(requireActivity())
//        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
//                InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }

}