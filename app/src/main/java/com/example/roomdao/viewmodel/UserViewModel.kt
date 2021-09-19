package com.example.roomdao.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.roomdao.model.User
import com.example.roomdao.data.UserDataBase
import com.example.roomdao.repository.UserDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllUserData:LiveData<MutableList<User>>
   private val repository: UserDataRepository

   init {
       val userDao = UserDataBase.getUserDataBase(application).userDao()
       repository = UserDataRepository(userDao)
       readAllUserData = repository.readAllData
   }

    fun addUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.addUser(user)
    }

    fun updateUser(user:User) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateUser(user)

    }

    fun deleteUser(user:User) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteUser(user)
    }

    fun deleteAllUsers() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAllUsers()
    }


//    fun readUserData():LiveData<MutableList<User>>{
//        return readAllUserData
//    }


    fun searchDataBase(searchQuery:String):LiveData<MutableList<User>>{
        return repository.searchDataBase(searchQuery)
    }

//    inner class UserViewModelFactory(private val repository: UserDataRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
//                @Suppress("UNCHECKED_CAST")
//                return UserViewModel(repository) as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//    }


}