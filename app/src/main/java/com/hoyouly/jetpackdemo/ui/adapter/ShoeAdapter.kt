package com.hoyouly.jetpackdemo.ui.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hoyouly.jetpackdemo.common.BaseConstant
import com.hoyouly.jetpackdemo.databinding.RecyclerItemShoeBinding
import com.hoyouly.jetpackdemo.db.data.Shoe
import com.hoyouly.jetpackdemo.ui.activity.DetailActivity

/**
 * @ Time  :  2020-08-26
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */


class ShoeAdapter constructor(val context: Context) :
    PagedListAdapter<Shoe, ShoeAdapter.ViewHodler>(ShoeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHodler {
        return ViewHodler(
            RecyclerItemShoeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHodler, position: Int) {
        val shoe = getItem(position)
        //apply可以操作一个对象的任意函数
        holder.apply {
            //bind()是 ViewHodler的一个函数
            //itemView 是 ViewHodler的一个变量
            bind(onCreateListener(shoe!!.id), shoe)
            itemView.tag = shoe
        }
    }

    private fun onCreateListener(id: Long): View.OnClickListener {
        return View.OnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(BaseConstant.DETAIL_SHOE_ID, id)
            context.startActivity(intent)
        }
    }

    //RecyclerItemShoeBinding
    class ViewHodler(private var binding: RecyclerItemShoeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: Shoe) {
            binding.apply {
                this.listener = listener
                this.shoe = item
                executePendingBindings()
            }
        }

    }
}
