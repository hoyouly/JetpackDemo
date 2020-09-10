package com.hoyouly.jetpackdemo.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
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



    // 删除一双鞋子
    @Delete
    fun deleteShoe(shoe: Shoe)

    // 删除多个鞋子
    // 参数也可以使用数组
    @Delete
    fun deleteShoes(shoes: List<Shoe>)

    // 更新一双鞋
    @Update
    fun updateShoe(shoe: Shoe)

    // 更新多双鞋
    // 参数也可以是集合
    @Update
    fun updateShoes(shoes: Array<Shoe>)



    // 通过鞋子的范围寻找Index
    @Query("SELECT * FROM shoe WHERE id BETWEEN :startIndex AND :endIndex")
    fun findShoeByIndexPage(startIndex: Long, endIndex: Long): List<Shoe>

    // 配合LiveData 返回所有的鞋子
    @Query("SELECT * FROM shoe")
    fun getAllShoeLD(): DataSource.Factory<Int, Shoe>

    @Query("SELECT * FROM shoe WHERE id=:id")
    fun findShoeByIdLD(id: Long): LiveData<Shoe>


    @Query("SELECT * FROM shoe WHERE shoe_brand=:brand")
    fun findShoeByBrandLD(brand: String): DataSource.Factory<Int, Shoe>


    @Query(
        "SELECT shoe.id,shoe.shoe_name,shoe.shoe_description,shoe.shoe_price, shoe.shoe_brand ,shoe.shoe_imgUrl FROM shoe " +
                " INNER JOIN favourite_shoe ON favourite_shoe.shoe_id =shoe.id WHERE favourite_shoe.user_id=:useid"
    )
    fun findShoeByUserId(useid: Long): LiveData<List<Shoe>>

}