package com.example.roomdao.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.roomdao.data.UserDao
import com.example.roomdao.model.User

class UserDataRepository(private val userDao: UserDao) {
    val readAllData :LiveData<MutableList<User>> = userDao.readAllData()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addUser(user: User){
        userDao.addUser(user)

    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user:User){
        userDao.deleteUser(user)
    }
    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }


    fun searchDataBase(searchQuery: String):LiveData<MutableList<User>>{
        return userDao.searchDataBase(searchQuery)
    }

}