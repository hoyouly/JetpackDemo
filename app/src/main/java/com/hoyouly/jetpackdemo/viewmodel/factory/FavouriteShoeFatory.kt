package com.hoyouly.jetpackdemo.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hoyouly.jetpackdemo.db.repository.FavouriteShoeRespository
import com.hoyouly.jetpackdemo.db.repository.ShoeRepository
import com.hoyouly.jetpackdemo.viewmodel.DetailModel

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class FavouriteShoeFatory(
    private val shoeRespository: ShoeRepository,
    private val favouriteShoeRespository: FavouriteShoeRespository,
    private val shoeId: Long,
    private val userId: Long
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailModel(shoeRespository, favouriteShoeRespository, shoeId, userId) as T;
    }
}