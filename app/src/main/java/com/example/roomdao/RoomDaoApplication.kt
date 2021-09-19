package com.example.roomdao

import android.app.Application
import com.example.roomdao.data.UserDataBase

class RoomDaoApplication :Application(){
     val dataBase by lazy { UserDataBase.getUserDataBase(this) }
//    val repository by lazy { UserDataRepository(dataBase.userDao()) }

}