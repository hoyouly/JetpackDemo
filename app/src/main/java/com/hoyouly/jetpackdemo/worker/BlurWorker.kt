package com.hoyouly.jetpackdemo.worker

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.hoyouly.jetpackdemo.common.BaseConstant.KEY_IMAGE_URI
import com.hoyouly.jetpackdemo.utils.blurBitmap
import com.hoyouly.jetpackdemo.utils.makeStatusNotification
import com.hoyouly.jetpackdemo.utils.writeBitmapToFile

/**
 * @ Time  :  2020-09-10
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

class BlurWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    private val TAG by lazy {
        //        this::class.java.simpleName
        "hoyouly"
    }

    override fun doWork(): Result {
        val context = applicationContext
        val resourceUrl = inputData.getString(KEY_IMAGE_URI)
        makeStatusNotification("Blurring image", context)

        return try {

            if (TextUtils.isEmpty(resourceUrl)) {
                Log.e(TAG, "Invalid input uri")
                throw IllegalArgumentException("Invalid input uri")
            }

            val resolver = context.contentResolver
            val pic = BitmapFactory.decodeStream(resolver.openInputStream(Uri.parse(resourceUrl)))
            val output = blurBitmap(pic, context)
            val outputUri = writeBitmapToFile(context, output)

            val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
            makeStatusNotification("Output is $outputUri", context)

            Result.success(outputData)
        } catch (exception: Exception) {
            Log.e(TAG, "Error applying blur", exception)

            Result.failure()
        }
    }
}

