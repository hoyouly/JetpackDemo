package com.hoyouly.jetpackdemo.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hoyouly.jetpackdemo.R
import com.hoyouly.jetpackdemo.databinding.FragmentRegisterBinding
import com.hoyouly.jetpackdemo.viewmodel.CustomViewModelProvider
import com.hoyouly.jetpackdemo.viewmodel.RegisterModel

/**
 * @ Time  :  2020-08-24
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class RegisterFragment : Fragment() {


    var isEnable: Boolean = false

    lateinit var binding: FragmentRegisterBinding

    private val registerModel: RegisterModel by viewModels {
        CustomViewModelProvider.providerRegistModel(requireContext(), findNavController())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRegisterBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_register,
                container,
                false
            )

        binding.activity = activity
        binding.model = registerModel
        binding.isEnable = isEnable
        this.binding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeVarargs: RegisterFragmentArgs by navArgs()
        var email = safeVarargs.email
        binding.model?.email?.value = email

        registerModel.p.observe(viewLifecycleOwner, Observer {
            binding.isEnable = it.isNotEmpty()
                    && registerModel.n.value!!.isNotEmpty()
                    && registerModel.email.value!!.isNotEmpty()
        })

        registerModel.n.observe(viewLifecycleOwner, Observer {
            binding.isEnable = it.isNotEmpty()
                    && registerModel.p.value!!.isNotEmpty()
                    && registerModel.email.value!!.isNotEmpty()

        })

        registerModel.email.observe(viewLifecycleOwner, Observer {
            binding.isEnable = it.isNotEmpty()
                    && registerModel.p.value!!.isNotEmpty()
                    && registerModel.n.value!!.isNotEmpty()

        })
    }


}