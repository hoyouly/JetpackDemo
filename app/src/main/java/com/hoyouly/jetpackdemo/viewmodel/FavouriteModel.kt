package com.hoyouly.jetpackdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hoyouly.jetpackdemo.db.data.Shoe
import com.hoyouly.jetpackdemo.db.repository.ShoeRepository

class FavouriteModel constructor(shoeRepository: ShoeRepository, userId:Long) : ViewModel() {

    // 鞋子集合的观察类
    val shoes: LiveData<List<Shoe>> = shoeRepository.getShoesByUserId(userId)

}