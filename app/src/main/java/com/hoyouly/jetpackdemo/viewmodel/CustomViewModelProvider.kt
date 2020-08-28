package com.hoyouly.jetpackdemo.viewmodel

import android.content.Context
import androidx.navigation.NavController
import com.hoyouly.jetpackdemo.db.RepositoryProvider
import com.hoyouly.jetpackdemo.db.repository.FavouriteShoeRespository
import com.hoyouly.jetpackdemo.db.repository.ShoeRepository
import com.hoyouly.jetpackdemo.db.repository.UserRepository
import com.hoyouly.jetpackdemo.viewmodel.factory.FavouriteShoeFatory
import com.hoyouly.jetpackdemo.viewmodel.factory.LoginModelFactory
import com.hoyouly.jetpackdemo.viewmodel.factory.RegisteModelFacotry
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


    fun providerRegistModel(context: Context, navController: NavController): RegisteModelFacotry {
        val repository: UserRepository = RepositoryProvider.providerUserRepository(context)
        return RegisteModelFacotry(repository, navController)
    }

    fun providerLoginModel(context: Context): LoginModelFactory {
        val repository: UserRepository = RepositoryProvider.providerUserRepository(context)
        return LoginModelFactory(repository, context)
    }


    fun providerDetailModel(context: Context, userId: Long, shoeId: Long): FavouriteShoeFatory {
        val favouriteShoeRespository: FavouriteShoeRespository =
            RepositoryProvider.providerFavouriteShoeRespository(context)
        val shoeRepository: ShoeRepository = RepositoryProvider.providerShoeRepository(context)

        return FavouriteShoeFatory(shoeRepository, favouriteShoeRespository, userId, shoeId)
    }
}