package com.hoyouly.jetpackdemo.ui.fragment.main

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hoyouly.jetpackdemo.common.listener.SimpleAnimatorListener
import com.hoyouly.jetpackdemo.databinding.FragmentShoeBinding
import com.hoyouly.jetpackdemo.ui.adapter.ShoeAdapter
import com.hoyouly.jetpackdemo.utils.UiUtils
import com.hoyouly.jetpackdemo.viewmodel.CustomViewModelProvider
import com.hoyouly.jetpackdemo.viewmodel.ShoeModel

/**
 * @ Time  :  2020-08-24
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class ShoeFragment : Fragment() {

    private val TAG by lazy {
        this::class.java.simpleName
    }
    private val viewModel: ShoeModel by viewModels {
        CustomViewModelProvider.providerShoeModel(requireContext())
    }

    private lateinit var mShoe: FloatingActionButton
    private lateinit var mNike: FloatingActionButton
    private lateinit var mAdi: FloatingActionButton
    private lateinit var mOther: FloatingActionButton

    private lateinit var nikeGroup: Group
    private lateinit var adiGroup: Group
    private lateinit var otherGroup: Group

    // 动画集合，用来控制动画的有序播放
    private var animatorSet: AnimatorSet? = null

    // 圆的半径
    private var radius: Int = 0
    // FloatingActionButton宽度和高度，宽高一样
    private var width: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeBinding = FragmentShoeBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val adapter = ShoeAdapter(context!!)
        binding.recycler.adapter = adapter
        onSubscribeUi(adapter, binding)
        return binding.root
    }

    private fun onSubscribeUi(adapter: ShoeAdapter, binding: FragmentShoeBinding) {
        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.submitList(it)
            }
        })

        mShoe = binding.fabShoe
        mNike = binding.fabNike
        mAdi = binding.fabAdidas
        mOther = binding.fabOther
        nikeGroup = binding.gbNike
        adiGroup = binding.gbAdidas
        otherGroup = binding.gbOther

        mNike.setOnClickListener {
            viewModel.setBrand(ShoeModel.NIKE)
            shoeAnimation()
        }

        mAdi.setOnClickListener {
            viewModel.setBrand(ShoeModel.ADIDAS)
            shoeAnimation()
        }

        mOther.setOnClickListener {
            viewModel.setBrand(ShoeModel.OTHER)
            shoeAnimation()
        }

        setViewVisible(false)
        initListener()
    }

    private fun initListener() {
        mShoe.setOnClickListener {
            shoeAnimation()
        }
    }

    override fun onResume() {
        super.onResume()
        mShoe.post {
            width = mShoe.measuredWidth
        }

        radius = UiUtils.dp2px(context!!, 80f)
    }

    private fun shoeAnimation() {

        if (animatorSet != null && animatorSet!!.isRunning) {
            return
        }

        if (nikeGroup.visibility != View.VISIBLE) {
            animatorSet = AnimatorSet()
            val nikeAnimator = getValueAnimator(mNike, false, nikeGroup, 0)
            val adiAnimator = getValueAnimator(mAdi, false, adiGroup, 45)
            val otherAnimator = getValueAnimator(mOther, false, otherGroup, 90)
            //字段后面加`!!` ,像java一样抛出空指针异常
            animatorSet!!.playSequentially(nikeAnimator, adiAnimator, otherAnimator)
            animatorSet!!.start()
        } else {
            animatorSet = AnimatorSet()
            val nikeAnimator = getValueAnimator(mNike, true, nikeGroup, 0)
            val adiAnimator = getValueAnimator(mAdi, true, adiGroup, 45)
            val otherAnimator = getValueAnimator(mOther, true, otherGroup, 90)
            //字段后面加`!!` ,像java一样抛出空指针异常
            animatorSet!!.playSequentially(otherAnimator, adiAnimator, nikeAnimator)
            animatorSet!!.start()
        }
    }

    private fun getValueAnimator(
        button: FloatingActionButton
        , reverse: Boolean
        , group: Group
        , angel: Int
    ): ValueAnimator {
        val animator: ValueAnimator
        if (reverse) {
            animator = ValueAnimator.ofFloat(1f, 0f)
        } else {
            animator = ValueAnimator.ofFloat(0f, 1f)
        }

        animator.addUpdateListener { animation ->

            val v = animation.animatedValue as Float

            val params = button.layoutParams as ConstraintLayout.LayoutParams
            params.circleRadius = (radius.toFloat() * v).toInt()
            params.circleAngle = 270f + angel * v
            params.width = (width.toFloat() * v).toInt()
            params.height = (width.toFloat() * v).toInt()
            button.layoutParams = params

            if (group == nikeGroup) {
                Log.e(
                    TAG,
                    "cirRadius:${params.circleRadius},angle:${params.circleAngle},width:${params.width}"
                )
            }
        }

        animator.addListener(object : SimpleAnimatorListener() {
            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                group.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if (group == nikeGroup && reverse) {
                    setViewVisible(false)
                }
            }
        })

        animator.duration = 100
        animator.interpolator = DecelerateInterpolator() as TimeInterpolator?
        return animator

    }

    private fun setViewVisible(isShow: Boolean) {
        nikeGroup.visibility = if (isShow) View.VISIBLE else View.GONE
        adiGroup.visibility = if (isShow) View.VISIBLE else View.GONE
        otherGroup.visibility = if (isShow) View.VISIBLE else View.GONE
    }

}