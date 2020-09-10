package com.hoyouly.jetpackdemo.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import androidx.annotation.WorkerThread
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.hoyouly.jetpackdemo.R
import com.hoyouly.jetpackdemo.common.BaseConstant.CHANNEL_ID
import com.hoyouly.jetpackdemo.common.BaseConstant.NOTIFICATION_ID
import com.hoyouly.jetpackdemo.common.BaseConstant.NOTIFICATION_TITLE
import com.hoyouly.jetpackdemo.common.BaseConstant.OUTPUT_PATH
import com.hoyouly.jetpackdemo.common.BaseConstant.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
import com.hoyouly.jetpackdemo.common.BaseConstant.VERSION_NOTIFICATION_CHANNEL_NAME
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.util.*

/**
 * @ Time  :  2020-09-10
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

fun makeStatusNotification(message: String, context: Context) {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {

        val name = VERSION_NOTIFICATION_CHANNEL_NAME
        val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION

        val importance = NotificationManager.IMPORTANCE_HIGH

        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = description

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)
    }

    val builder =
        NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_account)
            .setContentTitle(NOTIFICATION_TITLE)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(LongArray(0))

    NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
}


@WorkerThread
fun blurBitmap(bitmap: Bitmap, context: Context): Bitmap {
    lateinit var resContext: RenderScript
    try {
        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        resContext = RenderScript.create(context, RenderScript.ContextType.DEBUG)
        val inAlloc = Allocation.createFromBitmap(resContext, bitmap)
        val outAllo = Allocation.createTyped(resContext, inAlloc.type)
        val theIntrinsic = ScriptIntrinsicBlur.create(resContext, Element.U8_4(resContext))

        theIntrinsic.apply {
            setRadius(10f)
            theIntrinsic.setInput(inAlloc)
            theIntrinsic.forEach(outAllo)
        }
        outAllo.copyTo(output)
        return output
    } finally {
        resContext.finish()
    }
}


@Throws(FileNotFoundException::class)
fun writeBitmapToFile(context: Context, bitmap: Bitmap): Uri {
    val name = String.format("blur-filter-output-%s.png", UUID.randomUUID().toString())

    val outputDir = File(context.filesDir, OUTPUT_PATH)
    if (!outputDir.exists()) {
        outputDir.mkdirs()
    }
    val outputFile = File(outputDir, name)
    var out: FileOutputStream? = null
    try {
        out = FileOutputStream(outputFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, out)
    } finally {
        out?.let {
            try {
                it.close()
            } catch (ignore: Exception) {

            }
        }
    }

    return Uri.fromFile(outputFile)
}