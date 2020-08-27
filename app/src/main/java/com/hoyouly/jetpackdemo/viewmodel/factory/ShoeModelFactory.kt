package com.hoyouly.jetpackdemo.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hoyouly.jetpackdemo.db.repository.ShoeRepository
import com.hoyouly.jetpackdemo.viewmodel.ShoeModel

/**
 * @ Time  :  2020-08-26
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class ShoeModelFactory(private val shoeRepository: ShoeRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //使用 as 进行类型转换，可能会抛异常
        //使用 as? 进行安全的类型转换
        return ShoeModel(shoeRepository) as T
    }
}