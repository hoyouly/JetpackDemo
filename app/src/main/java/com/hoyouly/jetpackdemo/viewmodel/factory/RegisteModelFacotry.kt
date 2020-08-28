package com.hoyouly.jetpackdemo.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.hoyouly.jetpackdemo.db.repository.UserRepository
import com.hoyouly.jetpackdemo.viewmodel.RegisterModel

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class RegisteModelFacotry(
    private val userRepository: UserRepository,
    private val navController: NavController
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterModel(navController, userRepository) as T
    }

}