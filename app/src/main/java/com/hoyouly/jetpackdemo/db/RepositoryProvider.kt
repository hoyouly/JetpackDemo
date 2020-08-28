package com.hoyouly.jetpackdemo.db

import android.content.Context
import com.hoyouly.jetpackdemo.db.repository.FavouriteShoeRespository
import com.hoyouly.jetpackdemo.db.repository.ShoeRepository
import com.hoyouly.jetpackdemo.db.repository.UserRepository

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


    fun providerUserRepository(context: Context): UserRepository {
        return UserRepository.getInstance(AppDataBase.getInstance(context).userDao())
    }

    fun providerFavouriteShoeRespository(context: Context): FavouriteShoeRespository {
        return FavouriteShoeRespository.getInstance(AppDataBase.getInstance(context).favouriteShoeDao())
    }
}