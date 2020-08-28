package com.hoyouly.jetpackdemo.db.repository

import androidx.lifecycle.LiveData
import com.hoyouly.jetpackdemo.db.dao.FavouriteShoeDao
import com.hoyouly.jetpackdemo.db.data.FavouriteShoe

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

class FavouriteShoeRespository private constructor(private val favouriteShoeDao: FavouriteShoeDao) {

    fun findFavouriteShoeByUserIdAndShoeId(userId: Long, shoeId: Long): LiveData<FavouriteShoe?> =
        favouriteShoeDao.findFavouriteShoeByUserIdAndShoeId(userId, shoeId)


    companion object {
        @Volatile
        private var instance: FavouriteShoeRespository? = null

        fun getInstance(favouriteShoeDao: FavouriteShoeDao): FavouriteShoeRespository =
            instance ?: synchronized(this) {
                instance ?: FavouriteShoeRespository(favouriteShoeDao).also {
                    instance = it
                }
            }

    }
}