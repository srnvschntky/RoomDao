package com.example.roomdao.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdao.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user:User)

    @Query("DELETE FROM userTable")
    suspend fun deleteAllUsers()


    @Query("SELECT * FROM userTable WHERE firstName LIKE:searchQuery OR email LIKE:searchQuery")
    fun searchDataBase(searchQuery: String):LiveData<MutableList<User>>


    @Query("SELECT * FROM userTable ORDER BY id ASC")
    fun readAllData():LiveData<MutableList<User>>


}