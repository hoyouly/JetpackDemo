package com.hoyouly.jetpackdemo.ui.fragment.login

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

/**
 * @ Time  :  2020-08-24
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class RegisterFragment : Fragment() {

    lateinit var mTvCancel: TextView
    lateinit var mBtnRegister: Button
    lateinit var mEtEmail: EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTvCancel = view.findViewById(R.id.txt_cancel)
        mBtnRegister = view.findViewById(R.id.btn_register)
        mEtEmail = view.findViewById(R.id.et_email)
        mBtnRegister.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                activity,
                "注册",
                Toast.LENGTH_SHORT
            ).show()
        })
        mTvCancel.setOnClickListener({
            activity?.onBackPressed()
        })

        val safeArge: RegisterFragmentArgs by navArgs();
        val email = safeArge.email
        mEtEmail.setText(email)
    }


}