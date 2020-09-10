package com.hoyouly.jetpackdemo.db.datasource

import androidx.paging.DataSource
import com.hoyouly.jetpackdemo.db.data.Shoe
import com.hoyouly.jetpackdemo.db.repository.ShoeRepository

/**
 * @ Time  :  2020-09-09
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

class CustomPageDataSourceFactory (val shoeRepository: ShoeRepository):DataSource.Factory<Int,Shoe>(){
    override fun create(): DataSource<Int, Shoe> {
        return CustomPageDataSource(shoeRepository)
    }

}