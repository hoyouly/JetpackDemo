package com.hoyouly.jetpackdemo.utils

import android.content.Context
import android.content.SharedPreferences
import com.hoyouly.jetpackdemo.common.BaseApplication
import com.hoyouly.jetpackdemo.common.BaseConstant

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

object AppPrefsUtils {
    private var sp: SharedPreferences =
        BaseApplication.context.getSharedPreferences(BaseConstant.TABLE_PREFS, Context.MODE_PRIVATE)

    private var ed: SharedPreferences.Editor

    init {
        ed = sp.edit()
    }


    fun putBoolean(key: String, value: Boolean) {
        ed.putBoolean(key, value)
        ed.commit()
    }

    fun getBoolean(key: String): Boolean {
        return sp.getBoolean(key, true)
    }

    /*
       String数据
    */
    fun putString(key: String, value: String) {
        ed.putString(key, value)
        ed.commit()
    }

    /*
        默认 ""
     */
    fun getString(key: String): String? {
        return sp.getString(key, "")
    }

    /*
        Int数据
     */
    fun putInt(key: String, value: Int) {
        ed.putInt(key, value)
        ed.commit()
    }

    /*
        默认 0
     */
    fun getInt(key: String): Int {
        return sp.getInt(key, 0)
    }

    /*
        Long数据
     */
    fun putLong(key: String, value: Long) {
        ed.putLong(key, value)
        ed.commit()
    }

    /*
        默认 0
     */
    fun getLong(key: String): Long {
        return sp.getLong(key, 0)
    }

    fun putStringSet(key: String, set: Set<String>) {
        val localSet = getStringSet(key)?.toMutableSet()
        localSet?.addAll(set)
        ed.putStringSet(key, localSet)
        ed.commit()
    }

    fun getStringSet(key: String): Set<String>? {
        val set = setOf<String>()
        return sp.getStringSet(key, set)
    }

}