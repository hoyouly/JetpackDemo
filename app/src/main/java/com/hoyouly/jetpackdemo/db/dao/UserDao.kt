package com.hoyouly.jetpackdemo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hoyouly.jetpackdemo.db.data.User

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE user_account=:account AND user_pwd=:pwd")
    fun login(account: String, pwd: String): LiveData<User?>


    @Query("SELECT * FROM user WHERE id=:id")
    fun findUserById(id: Long): LiveData<User>

    @Query("SELECT * FROM USER")
    fun getAllUsers(): List<User>

    @Insert
    fun insertUser(user: User): Long


    @Delete
    fun deleteUser(user: User)


}