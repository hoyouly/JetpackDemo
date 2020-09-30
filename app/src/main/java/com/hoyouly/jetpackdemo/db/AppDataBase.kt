package com.hoyouly.jetpackdemo.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.hoyouly.jetpackdemo.db.dao.FavouriteShoeDao
import com.hoyouly.jetpackdemo.db.dao.ShoeDao
import com.hoyouly.jetpackdemo.db.dao.UserDao
import com.hoyouly.jetpackdemo.db.data.FavouriteShoe
import com.hoyouly.jetpackdemo.db.data.Shoe
import com.hoyouly.jetpackdemo.db.data.User
import com.hoyouly.jetpackdemo.utils.ShoeWorker

/**
 * @ Time  :  2020-08-26
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

@Database(
    entities = [Shoe::class, User::class, FavouriteShoe::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {


    abstract fun shoeDao(): ShoeDao

    abstract fun userDao(): UserDao

    abstract fun favouriteShoeDao(): FavouriteShoeDao

    //一个companion object（伴随对象） 让这个类的所有对象共享这个伴随对象（object在Kotlin中用来表示单例，Kotlin用Any来表示所有类的基类）
    companion object {
        private val TAG by lazy {
//        ShoeWorker::class.java.simpleName
            "hoyouly"
        }
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {

            return instance ?: synchronized(this) {
                instance ?: buildDataBase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDataBase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, "jetpackdemo.db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.e(TAG, "buildDataBase :")
                        val request = OneTimeWorkRequestBuilder<ShoeWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                }).build()
        }
    }
}