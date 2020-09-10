package com.hoyouly.jetpackdemo.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.hoyouly.jetpackdemo.common.BaseConstant.OUTPUT_PATH
import com.hoyouly.jetpackdemo.utils.makeStatusNotification
import java.io.File

/**
 * @ Time  :  2020-09-10
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description : 清理临时文件的workder
 */

class CleanUpWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    private val TAG by lazy {
//        this::class.java.simpleName
        "hoyouly"
    }

    override fun doWork(): Result {
        makeStatusNotification("clean old temporary file", applicationContext)
        return try {
            val outputFile = File(applicationContext.filesDir, OUTPUT_PATH)
            if (outputFile.exists()) {
                val entries = outputFile.listFiles()
                if (entries != null) {
                    for (file in entries) {
                        val name = file.name
                        if (name.isNotEmpty() && name.endsWith(".png")) {
                            val delete = file.delete()
                            Log.i(TAG, String.format("Deleted %s - %s", name, delete))
                        }
                    }
                }
            }
            Result.success()

        } catch (e: Exception) {
            Log.e(TAG, "Error cleaning up", e)
            Result.failure()
        }
    }

}