package com.hoyouly.jetpackdemo.ui.activity

import android.animation.Animator
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hoyouly.jetpackdemo.R
import com.hoyouly.jetpackdemo.common.BaseConstant
import com.hoyouly.jetpackdemo.common.listener.SimpleAnimatorListener
import com.hoyouly.jetpackdemo.databinding.ActivityDetailBinding
import com.hoyouly.jetpackdemo.utils.AppPrefsUtils
import com.hoyouly.jetpackdemo.viewmodel.CustomViewModelProvider
import com.hoyouly.jetpackdemo.viewmodel.DetailModel

/**
 * @ Time  :  2020-09-11
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

class DetailActivity : AppCompatActivity() {

    private val detailModel: DetailModel by viewModels {
        CustomViewModelProvider.providerDetailModel(
            this,
            intent.getLongExtra(BaseConstant.DETAIL_SHOE_ID, 1L),
            AppPrefsUtils.getLong(BaseConstant.SP_USER_ID)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)

        binding.model = detailModel
        initListener(binding)
    }

    private fun initListener(binding: ActivityDetailBinding) {

        binding.lifecycleOwner = this
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.fbFavourite.setOnClickListener {
            binding.fbFavourite.animate()
                .rotation(360.0f)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .setListener(object : SimpleAnimatorListener() {
                    override fun onAnimationEnd(animation: Animator?) {
                        detailModel.favourite()
                    }
                })
                .setDuration(200)
                .start()
        }
    }
}