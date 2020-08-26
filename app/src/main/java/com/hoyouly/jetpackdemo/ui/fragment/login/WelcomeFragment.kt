package com.hoyouly.jetpackdemo.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.hoyouly.jetpackdemo.R

/**
 * @ Time  :  2020-08-24
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    lateinit var btnLogin: Button
    lateinit var btnRegister: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin = view.findViewById(R.id.btn_login)
        btnRegister = view.findViewById(R.id.btn_register)
        btnLogin.setOnClickListener {
            val navOption = navOptions {
                anim {
                    enter = R.anim.slide_in_right
                    exit = R.anim.slide_out_left
                    popEnter = R.anim.slide_in_left
                    popExit = R.anim.slide_out_right
                }
            }
            val bundle = Bundle();
            bundle.putString("name", "TeaOf")
            findNavController().navigate(R.id.login, bundle, navOption)
        }

        btnRegister.setOnClickListener {
            val action =
                WelcomeFragmentDirections.actionWelcomeToRegister().setEMAIL("TeaOf1995@Gamil.com")
            findNavController().navigate(action)
        }
    }
}