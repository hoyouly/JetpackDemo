package com.hoyouly.jetpackdemo.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hoyouly.jetpackdemo.db.data.Shoe

/**
 * @ Time  :  2020-09-11
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class ShoeDiffCallback : DiffUtil.ItemCallback<Shoe>() {
    override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem == newItem
    }

}