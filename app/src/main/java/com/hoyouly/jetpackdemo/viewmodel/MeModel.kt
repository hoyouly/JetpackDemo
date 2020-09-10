package com.hoyouly.jetpackdemo.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.hoyouly.jetpackdemo.common.BaseConstant.IMAGE_MANIPULATION_WORK_NAME
import com.hoyouly.jetpackdemo.common.BaseConstant.KEY_IMAGE_URI
import com.hoyouly.jetpackdemo.common.BaseConstant.SP_USER_ID
import com.hoyouly.jetpackdemo.common.BaseConstant.TAG_OUTPUT
import com.hoyouly.jetpackdemo.db.repository.UserRepository
import com.hoyouly.jetpackdemo.utils.AppPrefsUtils
import com.hoyouly.jetpackdemo.worker.BlurWorker
import com.hoyouly.jetpackdemo.worker.CleanUpWorker
import com.hoyouly.jetpackdemo.worker.SaveImageToFileWorker
import kotlinx.coroutines.launch

/**
 * @ Time  :  2020-09-10
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

class MeModel(val userRepository: UserRepository) : ViewModel() {


    internal var imageUri: Uri? = null
    internal var outputUri: Uri? = null
    internal val outputWorkerInfos: LiveData<List<WorkInfo>>

    private val workerManger = WorkManager.getInstance()

    val user = userRepository.findUserById(AppPrefsUtils.getLong(SP_USER_ID))


    init {
        outputWorkerInfos = workerManger.getWorkInfosByTagLiveData(TAG_OUTPUT)
    }

    internal fun applyBlur(blurLevel: Int) {
        // 多任务按顺序执行
        var continuation = workerManger.beginUniqueWork(
            IMAGE_MANIPULATION_WORK_NAME,// 任务名称
            ExistingWorkPolicy.REPLACE,// 任务相同的执行策略 分为REPLACE，KEEP，APPEND
            // OneTimeWorkRequest  只执行一次的任务请求，支持任务链
            OneTimeWorkRequest.from(CleanUpWorker::class.java)
        )

        for (i in 0 until blurLevel) {
            val builder = OneTimeWorkRequestBuilder<BlurWorker>()
            if (i == 0) {
                builder.setInputData(creatInputDataFromUri())
            }
            // 多任务按顺序执行, 使用任务链
            continuation = continuation.then(builder.build())
        }
        // 构建约束条件
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true) // 非电池低电量
            .setRequiredNetworkType(NetworkType.CONNECTED)// 网络连接的情况
            .setRequiresStorageNotLow(true)// 存储空间足
            .build()

        // 储存照片
        val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
            .setConstraints(constraints)
            .addTag(TAG_OUTPUT)
            .build()

        // 多任务按顺序执行, 使用任务链
        continuation = continuation.then(save)
        continuation.enqueue()
    }

    private fun creatInputDataFromUri(): Data {
        val builder = Data.Builder()
        imageUri?.let {
            builder.putString(KEY_IMAGE_URI, imageUri.toString())
        }
        return builder.build()

    }

    private fun uriOrNull(uriString: String?): Uri? {
        return if (!uriString.isNullOrEmpty()) {
            Uri.parse(uriString)
        } else {
            null
        }
    }


    fun cancelWork() {
        workerManger.cancelAllWorkByTag(IMAGE_MANIPULATION_WORK_NAME)
    }

    fun setImageUri(uri: String) {
        imageUri = uriOrNull(uri)
    }

    internal fun setOutputUri(uri: String) {
        outputUri = uriOrNull(uri)
        val value = user.value

        value?.headImage = uri!!
        if (value != null) {
            viewModelScope.launch {
                userRepository.updateUser(value)
            }
        }

    }

}