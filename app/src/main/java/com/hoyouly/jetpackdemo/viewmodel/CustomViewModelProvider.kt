package com.hoyouly.jetpackdemo.viewmodel

import android.content.Context
import androidx.navigation.NavController
import com.hoyouly.jetpackdemo.common.BaseConstant
import com.hoyouly.jetpackdemo.db.RepositoryProvider
import com.hoyouly.jetpackdemo.db.repository.FavouriteShoeRespository
import com.hoyouly.jetpackdemo.db.repository.ShoeRepository
import com.hoyouly.jetpackdemo.db.repository.UserRepository
import com.hoyouly.jetpackdemo.utils.AppPrefsUtils
import com.hoyouly.jetpackdemo.viewmodel.factory.*

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

    fun providerMeModel(context: Context): MeModelFactory {
        val repository: UserRepository = RepositoryProvider.providerUserRepository(context)
        return MeModelFactory(repository)
    }

    fun providerDetailModel(context: Context, shoeId: Long, userId: Long): FavouriteShoeFatory {
        val favouriteShoeRespository: FavouriteShoeRespository =
            RepositoryProvider.providerFavouriteShoeRespository(context)
        val shoeRepository: ShoeRepository = RepositoryProvider.providerShoeRepository(context)

        return FavouriteShoeFatory(shoeRepository, favouriteShoeRespository, shoeId, userId)
    }

    fun providerFavouriteModel(context: Context): FavouriteModelFactory {
        val repository: ShoeRepository = RepositoryProvider.providerShoeRepository(context)
        val userId: Long = AppPrefsUtils.getLong(BaseConstant.SP_USER_ID)
        return FavouriteModelFactory(repository, userId)
    }
}