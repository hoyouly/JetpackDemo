package com.hoyouly.jetpackdemo.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hoyouly.jetpackdemo.db.repository.UserRepository
import com.hoyouly.jetpackdemo.viewmodel.MeModel

class MeModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MeModel(repository) as T
    }
}