package com.hoyouly.jetpackdemo.viewmodel

import android.content.Context
import com.hoyouly.jetpackdemo.db.RepositoryProvider
import com.hoyouly.jetpackdemo.db.repository.ShoeRepository
import com.hoyouly.jetpackdemo.viewmodel.factory.ShoeModelFactory

/**
 * @ Time  :  2020-08-26
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
object CustomViewModelProvider {

    fun providerShoeModel(context: Context): ShoeModelFactory {
        val repository: ShoeRepository = RepositoryProvider.providerShoeRepository(context)
        return ShoeModelFactory(repository)
    }
}