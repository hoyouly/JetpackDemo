package com.hoyouly.jetpackdemo.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hoyouly.jetpackdemo.databinding.FragmentShoeBinding
import com.hoyouly.jetpackdemo.ui.adapter.ShoeAdapter
import com.hoyouly.jetpackdemo.viewmodel.CustomViewModelProvider
import com.hoyouly.jetpackdemo.viewmodel.ShoeModel

/**
 * @ Time  :  2020-08-24
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class ShoeFragment : Fragment() {

    private val viewModel: ShoeModel by viewModels {
        CustomViewModelProvider.providerShoeModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeBinding = FragmentShoeBinding.inflate(inflater, container, false)
        context ?: return binding.root
//        ViewModelProviders.of(this).get(ShoeModel::class.java)
        val adapter = ShoeAdapter()
        binding.recycler.adapter = adapter
        onSubscribeUi(adapter)
        return binding.root
    }

    private fun onSubscribeUi(adapter: ShoeAdapter) {
        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.submitList(it)
            }
        })
    }

}