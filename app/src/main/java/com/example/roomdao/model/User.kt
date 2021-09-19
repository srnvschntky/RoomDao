package com.example.roomdao.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "userTable")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id:Int,

    @ColumnInfo(name="firstName")
    val firstName:String,

    @ColumnInfo(name="lastName")
    val lastName:String,

    @ColumnInfo(name="email")
    val email:String,

    @ColumnInfo(name="phone")
    val phone:String,

    @ColumnInfo(name="address")
    val address:String,

    @ColumnInfo(name = "gender")
    val gender:String,

    @ColumnInfo(name ="age")
    val age:Int
):Parcelable
