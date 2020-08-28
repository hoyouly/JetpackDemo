package com.hoyouly.jetpackdemo.viewmodel

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.hoyouly.jetpackdemo.MainActivity
import com.hoyouly.jetpackdemo.common.BaseConstant
import com.hoyouly.jetpackdemo.common.listener.SimpleWatcher
import com.hoyouly.jetpackdemo.db.repository.UserRepository
import com.hoyouly.jetpackdemo.utils.AppPrefsUtils

/**
 * @ Time  :  2020-08-25
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class LoginModel constructor(
    private val respository: UserRepository,
    private val context: Context
) : ViewModel() {
    //ObservableField 是一个可观察的域，通过泛型来使用
    val n = MutableLiveData<String>()
    val p = MutableLiveData<String>()

    lateinit var lifecycleOwner: LifecycleOwner

    /**
     * 用户名改变的回调
     */
    fun onNameChanged(s: CharSequence) {
        n.value = s.toString()
    }

    /**
     * 密码改变的回调函数
     */
    fun onPwdChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        p.value = s.toString()
    }

    /**
     * 登录
     */
    fun login() {
        if (TextUtils.isEmpty(n.value) || TextUtils.isEmpty(p.value)) {
            return
        }

        val pwd = p.value!!
        val account = n.value!!

        respository.login(account, pwd).observe(lifecycleOwner, Observer {
            if (it != null) {
                AppPrefsUtils.putLong(BaseConstant.SP_USER_ID, it.id)
                Toast.makeText(context, "账号密码正确", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }
        })
    }

    // SimpleWatcher 是简化了的TextWatcher
    var nameWatcher = object : SimpleWatcher() {

        override fun afterTextChanged(p0: Editable?) {
            super.afterTextChanged(p0)
            n.value = p0.toString()
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("addTextChangedListener")
        fun addTextChangedListener(editText: EditText, simpleWatcher: SimpleWatcher) {
            editText.addTextChangedListener(simpleWatcher)
        }
    }


}