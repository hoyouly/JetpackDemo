package com.hoyouly.jetpackdemo.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.hoyouly.jetpackdemo.db.AppDataBase
import com.hoyouly.jetpackdemo.db.dao.FavouriteShoeDao
import com.hoyouly.jetpackdemo.db.dao.ShoeDao
import com.hoyouly.jetpackdemo.db.dao.UserDao
import com.hoyouly.jetpackdemo.utils.ShoeWorker
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
object RoomModule {

    private val TAG by lazy {
        "hoyouly"
    }
    @Singleton
    @Provides
    fun providerAppDataBase(application: Application): AppDataBase {
        return Room.databaseBuilder(application, AppDataBase::class.java, "jetpackdemo.db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.e(TAG, "RoomModule  providerAppDataBase:")
                    val request = OneTimeWorkRequestBuilder<ShoeWorker>().build()
                    WorkManager.getInstance(application).enqueue(request)
                }
            }).build()
    }

    @Singleton
    @Provides
    fun providerShoeDao(dataBase: AppDataBase): ShoeDao {
        return dataBase.shoeDao()
    }

    @Singleton
    @Provides
    fun providerUserDao(dataBase: AppDataBase): UserDao {
        return dataBase.userDao()
    }

    @Singleton
    @Provides
    fun providerFavouriteShoeDao(dataBase: AppDataBase): FavouriteShoeDao {
        return dataBase.favouriteShoeDao()
    }

}