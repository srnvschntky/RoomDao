package com.example.roomdao.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdao.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase:RoomDatabase() {
    abstract fun userDao():UserDao

    companion object{
        @Volatile
        private var INSTANCE:UserDataBase? = null
        fun getUserDataBase(context: Context):UserDataBase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "userDataBase"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }

        }
    }
}