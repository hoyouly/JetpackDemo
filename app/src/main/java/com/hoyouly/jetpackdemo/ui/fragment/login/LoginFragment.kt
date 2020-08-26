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
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {

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