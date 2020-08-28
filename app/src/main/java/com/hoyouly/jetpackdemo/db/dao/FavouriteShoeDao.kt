package com.hoyouly.jetpackdemo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hoyouly.jetpackdemo.db.data.FavouriteShoe

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
@Dao
interface FavouriteShoeDao {


    @Query("SELECT * FROM favourite_shoe WHERE user_id=:userId")
    fun findFavouriteShoeByUserId(userId: Long): LiveData<List<FavouriteShoe>>


    @Query("SELECT * FROM favourite_shoe WHERE user_id=:userId AND shoe_id=:shoeId")
    fun findFavouriteShoeByUserIdAndShoeId(userId: Long, shoeId: Long): LiveData<FavouriteShoe?>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteShoe(favouriteShoe: FavouriteShoe)

    @Delete
    fun deleteFavouriteShoe(favouriteShoe: FavouriteShoe)


    @Delete
    fun deleteFavouriteShoes(favouriteShoes: List<FavouriteShoe>)


}