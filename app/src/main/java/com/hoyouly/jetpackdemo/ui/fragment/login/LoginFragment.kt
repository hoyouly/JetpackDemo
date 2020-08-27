package com.hoyouly.jetpackdemo.ui.fragment.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hoyouly.jetpackdemo.databinding.FragmentLoginBinding
import com.hoyouly.jetpackdemo.viewmodel.LoginModel

/**
 * @ Time  :  2020-08-24
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

class LoginFragment : Fragment() {
    //lateinit 延迟加载，和lazy一样，只不过lazy只适用于val对象，对于var对象，需要使用lateinit，原理是类似的，
    lateinit var loginModel: LoginModel;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        loginModel = LoginModel("", "", context!!)



        binding.model = loginModel
        binding.activity = activity

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString("name")
        if (!TextUtils.isEmpty(name)) {
            loginModel.n.set(name)
        }
    }
}