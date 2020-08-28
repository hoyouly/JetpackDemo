package com.hoyouly.jetpackdemo.viewmodel

import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.hoyouly.jetpackdemo.R
import com.hoyouly.jetpackdemo.db.repository.UserRepository
import kotlinx.coroutines.launch

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

class RegisterModel constructor(
    private var navController: NavController,
    private var repository: UserRepository
) : ViewModel() {

    val n = MutableLiveData<String>("")
    val p = MutableLiveData<String>("")
    val email = MutableLiveData<String>("")

    /**
     * 用户名改变回调的函数
     */
    fun onNameChanged(s: CharSequence) {
        n.value = s.toString()
    }

    /**
     * 密码改变的回调函数
     */
    fun onPwdChanged(s: CharSequence) {
        p.value = s.toString()
    }

    /**
     * 邮箱改变的时候
     */
    fun onEmailChanged(s: CharSequence) {
        email.value = s.toString()
    }

    fun regist() {
        if (TextUtils.isEmpty(n.value) || TextUtils.isEmpty(p.value) || TextUtils.isEmpty(email.value)) {
            return
        }

        viewModelScope.launch {
            val id = repository.regiest(email.value!!, n.value!!, p.value!!)
            val user = repository.findUserById(id);
            val bundle = Bundle()

            bundle.putString("name", n.value)
            navController.navigate(R.id.login, bundle, null)
        }
    }


}