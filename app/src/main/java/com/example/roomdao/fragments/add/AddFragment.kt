package com.example.roomdao.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdao.R
import com.example.roomdao.model.User
import com.example.roomdao.databinding.FragmentAddBinding
import com.example.roomdao.viewmodel.UserViewModel

class AddFragment : Fragment() {
    private lateinit var userViewModel:UserViewModel
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        userViewModel =ViewModelProvider(this).get(UserViewModel::class.java)
        val items = listOf("MALE","FEMALE")
        val adapter = ArrayAdapter(requireContext(), R.layout.gender_layout, items)
        binding.textField.setAdapter(adapter)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_fragment_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addDone -> {

                this.findNavController().navigate(AddFragmentDirections.actionAddFragmentToListFragment())
                addToList()
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

    private fun addToList() {
        val user = User(id = 0, firstName = binding.firstname.text.toString(),
            lastName = binding.lastname.text.toString(),
            email = binding.email.text.toString(),
            phone = binding.phone.text.toString(),
            address = binding.address.text.toString(),
            age = binding.age.text.toString().toInt(),
            gender = binding.textField.text.toString()
            )

        userViewModel.addUser(user)
        Toast.makeText(requireContext(),"${user.firstName} is saved",Toast.LENGTH_SHORT).show()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
//        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
//                InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}