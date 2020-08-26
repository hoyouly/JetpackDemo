package com.hoyouly.jetpackdemo.common.listener

import android.text.Editable
import android.text.TextWatcher

/**
 * @ Time  :  2020-08-25
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
open class SimpleWatcher :TextWatcher{
    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

}