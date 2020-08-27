package com.hoyouly.jetpackdemo.viewmodel

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.hoyouly.jetpackdemo.MainActivity
import com.hoyouly.jetpackdemo.common.BaseConstant
import com.hoyouly.jetpackdemo.common.listener.SimpleWatcher

/**
 * @ Time  :  2020-08-25
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class LoginModel constructor(name: String, pwd: String, context: Context) {
    //ObservableField 是一个可观察的域，通过泛型来使用
    val n = ObservableField<String>(name)
    val p = ObservableField<String>(pwd)
    var context: Context = context

    /**
     * 用户名改变的回调
     */
    fun onNameChanged(s: CharSequence) {
        n.set(s.toString())
    }

    /**
     * 密码改变的回调函数
     */
    fun onPwdChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        p.set(s.toString())
    }

    /**
     * 登录
     */
    fun login() {
        if (n.get().equals(BaseConstant.USER_NAME) && p.get().equals(BaseConstant.USER_PWD)) {
            Toast.makeText(context, "账号密码正确", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    // SimpleWatcher 是简化了的TextWatcher
    var nameWatcher = object : SimpleWatcher() {

        override fun afterTextChanged(p0: Editable?) {
            super.afterTextChanged(p0)
            n.set(p0.toString())
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