package com.hoyouly.jetpackdemo.ui.fragment.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.hoyouly.jetpackdemo.common.BaseConstant.KEY_IMAGE_URI
import com.hoyouly.jetpackdemo.databinding.FragmentMeBinding
import com.hoyouly.jetpackdemo.viewmodel.CustomViewModelProvider
import com.hoyouly.jetpackdemo.viewmodel.MeModel

/**
 * @ Time  :  2020-08-24
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class MeFragment : Fragment() {

    private val TAG by lazy {
//        this::class.java.simpleName
        "hoyouly"
    }
    // 选择图片的标识
    private val REQUEST_CODE_IMAGE = 100

    private val model: MeModel by viewModels {
        CustomViewModelProvider.providerMeModel(requireContext())
    }

    private val sweetAlertDialog: SweetAlertDialog by lazy {
        SweetAlertDialog(requireContext(), SweetAlertDialog.PROGRESS_TYPE).setTitleText("头像")
            .setContentText("更新中...")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMeBinding = FragmentMeBinding.inflate(inflater, container, false)
        initListener(binding)
        onSubscribeUi(binding)
        return binding.root
    }

    private fun onSubscribeUi(binding: FragmentMeBinding) {
        binding.ivHead.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(intent, REQUEST_CODE_IMAGE)
        }

    }

    private fun initListener(binding: FragmentMeBinding) {
        model.user.observe(this, Observer {
            binding.user = it
        })

        model.outputWorkerInfos.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                return@Observer
            }

            val state = it[0]
            if (state.state.isFinished) {
                val outputImageUri = state.outputData.getString(KEY_IMAGE_URI)
                if (!outputImageUri.isNullOrEmpty()) {
                    model.setOutputUri(outputImageUri)
                }
                sweetAlertDialog.dismiss()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_IMAGE -> data?.let {
                    handleImageRequestResult(data)
                }
                else -> Log.d(TAG, "Unknown request code.")
            }
        } else {
            Log.e(TAG, String.format("Unexpected Result code %s", resultCode))

        }
    }

    //图片选择完成的处理
    private fun handleImageRequestResult(intent: Intent) {

        val imageUri: Uri? = intent.clipData?.let {
            it.getItemAt(0).uri
        } ?: intent.data

        if (imageUri == null) {
            Log.e(TAG, "Invalid input image Uri.")
            return
        }
        sweetAlertDialog.show()
        model.setImageUri(imageUri.toString())
        model.applyBlur(3)
    }

}