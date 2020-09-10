package com.hoyouly.jetpackdemo.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description : 用户表
 */
@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "user_account") val account: String
    , @ColumnInfo(name = "user_pwd") val pwd: String
    , @ColumnInfo(name = "user_name") val name: String
//    , @Embedded val address: Address  //Embedded 用于嵌套，里面的字段同样会存储在数据库中
//    , @Ignore val state: Int//状态只是临时用，所以不需要存储在数据库中
    , @ColumnInfo(name = "user_url") var headImage:String // 头像地址
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}