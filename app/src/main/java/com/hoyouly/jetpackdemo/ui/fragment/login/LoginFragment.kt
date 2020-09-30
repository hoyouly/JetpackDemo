package com.hoyouly.jetpackdemo.ui.fragment.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hoyouly.jetpackdemo.databinding.FragmentLoginBinding
import com.hoyouly.jetpackdemo.db.dao.UserDao
import com.hoyouly.jetpackdemo.db.repository.UserRepository
import com.hoyouly.jetpackdemo.viewmodel.LoginModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @ Time  :  2020-08-24
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

@AndroidEntryPoint
class LoginFragment : Fragment() {
    //lateinit 延迟加载，和lazy一样，只不过lazy只适用于val对象，对于var对象，需要使用lateinit，原理是类似的，
    private val loginModel: LoginModel by viewModels()
    var isEnable: Boolean = false
    lateinit var dataBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.model = loginModel
        binding.activity = activity
        binding.isEnable = isEnable
        this.dataBinding = binding
        loginModel.lifecycleOwner=viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginModel.p.observe(viewLifecycleOwner, Observer {
            //!! 如果 loginModel.n.value 是空的话， 抛出空指针异常
            dataBinding.isEnable = it.isNotEmpty() && loginModel.n.value!!.isNotEmpty()
        })
        val name = arguments?.getString("name")
        if (!TextUtils.isEmpty(name)) {
            loginModel.n.value = name
        }
    }
}