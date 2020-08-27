package com.hoyouly.jetpackdemo.db

import android.content.Context
import com.hoyouly.jetpackdemo.db.repository.ShoeRepository

/**
 * @ Time  :  2020-08-26
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
object RepositoryProvider {
    fun providerShoeRepository(context: Context): ShoeRepository {
        return ShoeRepository.getInstance(AppDataBase.getInstance(context).shoeDao())
    }
}