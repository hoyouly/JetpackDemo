package com.hoyouly.jetpackdemo.worker

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.hoyouly.jetpackdemo.common.BaseConstant.KEY_IMAGE_URI
import com.hoyouly.jetpackdemo.utils.makeStatusNotification
import java.text.SimpleDateFormat
import java.util.*

/**
 * @ Time  :  2020-09-10
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

class SaveImageToFileWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    private val TAG by lazy {
        //        this::class.java.simpleName
        "hoyouly"
    }


    private val Title = "Blurred Image"
    private val dateFormatter = SimpleDateFormat(
        "yyyy.MM.dd 'at' HH:mm:ss z",
        Locale.getDefault()
    )

    override fun doWork(): Result {
        makeStatusNotification("Saving image", applicationContext)

        val resolver = applicationContext.contentResolver

        return try {

            val resourceUri = inputData.getString(KEY_IMAGE_URI)
            val bitmap =
                BitmapFactory.decodeStream(resolver.openInputStream(Uri.parse(resourceUri)))


            Log.e(TAG, "resourceUri = $resourceUri ,   bitmap.width =${bitmap.width}")


            val imageUrl = MediaStore.Images.Media.insertImage(
                resolver, bitmap, Title, dateFormatter.format(
                    Date()
                )
            )
            if (!imageUrl.isNullOrEmpty()) {
                val output = workDataOf(KEY_IMAGE_URI to imageUrl)
                Result.success(output)
            } else {
                Log.e(TAG, "Writing to MediaStore failed")
                Result.failure()
            }

        } catch (exception: Exception) {
            Log.e(TAG, "Unable to save image to Gallery", exception)
            Result.failure()
        }
    }

}