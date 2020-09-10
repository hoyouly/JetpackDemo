package com.hoyouly.jetpackdemo.db.repository

import androidx.lifecycle.LiveData
import com.hoyouly.jetpackdemo.db.dao.UserDao
import com.hoyouly.jetpackdemo.db.data.User
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

class UserRepository private constructor(private val userDao: UserDao) {


    fun getAllUsers() = userDao.getAllUsers()

    fun findUserById(id: Long): LiveData<User> = userDao.findUserById(id)


    fun login(account: String, pwd: String): LiveData<User?> = userDao.login(account, pwd)


    fun deleteUser(user: User) = userDao.deleteUser(user)


    /**
     * 注册一个用户
     * suspend  挂起函数
     */
    suspend fun regiest(email: String, account: String, pwd: String): Long {
        //withContext 不会创建新的协程，在指定协程上运行挂起代码块，并挂起该协程直至代码块运行完成。
        return withContext(IO) {
            userDao.insertUser(
                User(
                    account,
                    pwd,
                    email,
                    "https://raw.githubusercontent.com/mCyp/Photo/master/1560651318109.jpeg"
                )
            )
        }
    }


    suspend fun updateUser(user: User) {
        withContext(IO) {
            userDao.insertUser(user)
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userDao).also {
                    instance = it;
                }
            }
    }
}