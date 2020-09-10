package com.hoyouly.jetpackdemo.utils

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.hoyouly.jetpackdemo.db.RepositoryProvider
import com.hoyouly.jetpackdemo.db.data.Shoe
import kotlinx.coroutines.coroutineScope

/**
 * @ Time  :  2020-08-26
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class ShoeWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    private val TAG by lazy {
        ShoeWorker::class.java.simpleName
    }

    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open("shoes.json").use {
                JsonReader(it.reader()).use {
                    val shoeType = object : TypeToken<List<Shoe>>() {}.type
                    val shoeList: List<Shoe> = Gson().fromJson(it, shoeType)

                    val shoeDao = RepositoryProvider.providerShoeRepository(applicationContext)

                    shoeDao.insertShoes(shoeList)

                    for (i in 0..2) {
                        for (shoe in shoeList) {
                            shoe.id += shoeList.size
                        }
                        shoeDao.insertShoes(shoeList)
                    }

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

}