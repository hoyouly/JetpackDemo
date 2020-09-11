package com.hoyouly.jetpackdemo.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hoyouly.jetpackdemo.databinding.FragmentFavouriteBinding
import com.hoyouly.jetpackdemo.ui.adapter.FavouriteAdapter
import com.hoyouly.jetpackdemo.viewmodel.CustomViewModelProvider
import com.hoyouly.jetpackdemo.viewmodel.FavouriteModel

/**
 * @ Time  :  2020-08-24
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class FavouriteFragment : Fragment() {


    private val viewModel: FavouriteModel by viewModels {
        CustomViewModelProvider.providerFavouriteModel(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFavouriteBinding =
            FragmentFavouriteBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val adapter = FavouriteAdapter(context!!)
        binding.recycler.adapter = adapter
        onSubscribeUi(adapter, binding)
        return binding.root
    }

    /**
     * 鞋子数据更新的通知
     */
    private fun onSubscribeUi(adapter: FavouriteAdapter, binding: FragmentFavouriteBinding) {
        binding.empty.bind(arrayOf(binding.recycler))
        binding.empty.triggerLoading()

        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            if (it != null && it.isNotEmpty()) {
                adapter.submitList(it)
            }
            binding.empty.triggerOkOrEmpty(adapter.itemCount > 0)
        })
    }
}