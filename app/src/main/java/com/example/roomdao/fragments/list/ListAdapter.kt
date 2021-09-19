package com.example.roomdao.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao.model.User
import com.example.roomdao.databinding.ListFragmentAdapterBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var userList:MutableList<User> = mutableListOf()
    fun setUserList(user: MutableList<User>) {
//        this.userList.clear()
        this.userList = user
        this.notifyDataSetChanged()
    }

    fun getDevicesList():MutableList<User>{
        return userList
    }
    class ViewHolder(private val binding:ListFragmentAdapterBinding):RecyclerView.ViewHolder(binding.root) {
        fun setUserData(user: User) {
            binding.cardFirstname.text = user.firstName.toString()
            binding.cardLastname.text = user.lastName.toString()
            binding.cardEmail.text = user.email.toString()
            binding.cardPhone.text = user.phone.toString()
            binding.cardAge.text = user.age.toString()

            binding.cardGender.text = user.gender.toString()

            binding.rowListFragment.setOnClickListener {

                val colr = binding.rowListFragment.cardBackgroundColor

              when(binding.rowListFragment.cardBackgroundColor){


              }
            }


            binding.rowListFragment.setOnLongClickListener {
                val action =ListFragmentDirections.actionListFragmentToUpdateFragment(user)
                binding.root.findNavController().navigate(action)
                return@setOnLongClickListener true
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListFragmentAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.setUserData(userList.elementAt(position))
    }

    override fun getItemCount(): Int {
        return userList.size

    }


}