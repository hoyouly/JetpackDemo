package com.hoyouly.jetpackdemo.db.data

import androidx.room.*
import java.util.*

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
@Entity(
    tableName = "favourite_shoe",
    foreignKeys = [//foreignKeys-外键
        ForeignKey(
            entity = Shoe::class,
            parentColumns = ["id"],
            childColumns = ["shoe_id"]
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        )
    ],
    indices = [Index("shoe_id")] //索引
)
data class FavouriteShoe(
    @ColumnInfo(name = "shoe_id") val shoeId: Long
    , @ColumnInfo(name = "user_id") val userId: Long
    , @ColumnInfo(name = "fav_date") val date: Calendar
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
/**
 * ColumnInfo 主要用来修改在数据库中的字段名。
 *
 */
