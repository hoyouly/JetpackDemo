package com.hoyouly.jetpackdemo.di

import com.hoyouly.jetpackdemo.db.dao.FavouriteShoeDao
import com.hoyouly.jetpackdemo.db.dao.ShoeDao
import com.hoyouly.jetpackdemo.db.dao.UserDao
import com.hoyouly.jetpackdemo.db.repository.FavouriteShoeRespository
import com.hoyouly.jetpackdemo.db.repository.ShoeRepository
import com.hoyouly.jetpackdemo.db.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * @ Time  :  2020/9/29
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providerUserRepository(userDao: UserDao): UserRepository {
        return UserRepository.getInstance(userDao)
    }

    @Singleton
    @Provides
    fun providerShoeRepository(shoeDao: ShoeDao): ShoeRepository {
        return ShoeRepository.getInstance(shoeDao)
    }

    @Singleton
    @Provides
    fun providerFavouriteShoeRespository(favouriteShoeDao: FavouriteShoeDao): FavouriteShoeRespository {
        return FavouriteShoeRespository.getInstance(favouriteShoeDao)
    }

}