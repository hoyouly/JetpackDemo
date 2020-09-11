package com.hoyouly.jetpackdemo.db.repository

import androidx.lifecycle.LiveData
import com.hoyouly.jetpackdemo.db.dao.FavouriteShoeDao
import com.hoyouly.jetpackdemo.db.data.FavouriteShoe
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.util.*

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

class FavouriteShoeRespository private constructor(private val favouriteShoeDao: FavouriteShoeDao) {

    /**
     * 查看某个用户是否有喜欢记录
     */
    fun findFavouriteShoeByUserIdAndShoeId(userId: Long, shoeId: Long): LiveData<FavouriteShoe?> =
        favouriteShoeDao.findFavouriteShoeByUserIdAndShoeId(userId, shoeId)

    suspend fun createFavouriteShoe(userId: Long, shoeId: Long) {
        withContext(IO) {
            favouriteShoeDao.insertFavouriteShoe(
                FavouriteShoe(
                    shoeId, userId,
                    Calendar.getInstance()
                )
            )
        }
    }

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