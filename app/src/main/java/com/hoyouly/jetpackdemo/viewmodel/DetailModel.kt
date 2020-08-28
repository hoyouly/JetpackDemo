package com.hoyouly.jetpackdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hoyouly.jetpackdemo.db.data.FavouriteShoe
import com.hoyouly.jetpackdemo.db.data.Shoe
import com.hoyouly.jetpackdemo.db.repository.FavouriteShoeRespository
import com.hoyouly.jetpackdemo.db.repository.ShoeRepository

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class DetailModel constructor(
    shoeRespository: ShoeRepository,
    favouriteShoeRespository: FavouriteShoeRespository,
    userId: Long,
    shoeId: Long
) : ViewModel() {

    val shoe: LiveData<Shoe> = shoeRespository.getShoeById(shoeId)

    val favouriteShoe: LiveData<FavouriteShoe?> =
        favouriteShoeRespository.findFavouriteShoeByUserIdAndShoeId(userId, shoeId)


}