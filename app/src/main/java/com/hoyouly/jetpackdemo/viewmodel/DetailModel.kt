package com.hoyouly.jetpackdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoyouly.jetpackdemo.db.data.FavouriteShoe
import com.hoyouly.jetpackdemo.db.data.Shoe
import com.hoyouly.jetpackdemo.db.repository.FavouriteShoeRespository
import com.hoyouly.jetpackdemo.db.repository.ShoeRepository
import kotlinx.coroutines.launch

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class DetailModel constructor(
    shoeRespository: ShoeRepository,
    private val favouriteShoeRespository: FavouriteShoeRespository,
    private val shoeId: Long,
    val userId: Long
) : ViewModel() {
    // 鞋
    val shoe: LiveData<Shoe> = shoeRespository.getShoeById(shoeId)

    // 收藏记录
    val favouriteShoe: LiveData<FavouriteShoe?> =
        favouriteShoeRespository.findFavouriteShoeByUserIdAndShoeId(userId, shoeId)

    // 收藏一双鞋
    fun favourite() {
        viewModelScope.launch {
            favouriteShoeRespository.createFavouriteShoe(userId, shoeId)
        }
    }
}