package com.hoyouly.jetpackdemo.db.repository

import com.hoyouly.jetpackdemo.db.dao.ShoeDao
import com.hoyouly.jetpackdemo.db.data.Shoe

/**
 * @ Time  :  2020-08-26
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class ShoeRepository private constructor(private val shoeDao: ShoeDao) {

    fun getAllShoes() = shoeDao.getAllShoes()

    fun getShoesByBrand(brand: String) = shoeDao.findShoeByBrand(brand)

    fun getShoeById(id:Long)=shoeDao.findShoeById(id)

    fun insertShoes(shoes: List<Shoe>) = shoeDao.insertShoes(shoes)

    companion object {

        private var intance: ShoeRepository? = null

        fun getInstance(shoeDao: ShoeDao): ShoeRepository {
            return intance ?: synchronized(this) {
                //如果 intance 为null的话，执行 ?: 后面的代码，不为null的话，就不再执行
                intance ?: ShoeRepository(shoeDao).also {
                    intance = it
                }
            }
        }
    }
}