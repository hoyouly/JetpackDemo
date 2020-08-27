package com.hoyouly.jetpackdemo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hoyouly.jetpackdemo.db.data.Shoe

/**
 * @ Time  :  2020-08-26
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
@Dao
interface ShoeDao {

    /**
     * 查询所有的鞋
     */
    @Query("SELECT * FROM shoe ")
    fun getAllShoes(): LiveData<List<Shoe>>

    /**
     * 根据ID查询对应的鞋子
     */
    @Query("SELECT * FROM shoe WHERE id=:id")
    fun findShoeById(id: Long): LiveData<Shoe>


    /**
     * 根据品牌查询对应的鞋子
     */
    @Query("SELECT * FROM shoe WHERE shoe_brand=:brand")
    fun findShoeByBrand(brand: String): LiveData<List<Shoe>>

    /**
     * 插入鞋子
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoe(shoe: Shoe)


    /**
     * 插入鞋子
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoes(shoes: List<Shoe>)
}