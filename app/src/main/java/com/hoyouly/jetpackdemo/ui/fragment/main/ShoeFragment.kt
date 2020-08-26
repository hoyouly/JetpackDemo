package com.hoyouly.jetpackdemo.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hoyouly.jetpackdemo.R
import com.hoyouly.jetpackdemo.ui.fragment.login.RegisterFragmentArgs

/**
 * @ Time  :  2020-08-24
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class ShoeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shoe, container, false)
    }

}