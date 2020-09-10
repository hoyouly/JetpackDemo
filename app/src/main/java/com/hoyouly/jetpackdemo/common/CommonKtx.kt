package com.hoyouly.jetpackdemo.common

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * @ Time  :  2020-09-10
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

fun <T> DataSource.Factory<Int, T>.createPagerList(
    pageSize: Int,
    defaultSize: Int
): LiveData<PagedList<T>> {
    return LivePagedListBuilder<Int, T>(
        this, PagedList.Config.Builder()
            .setPageSize(pageSize)// 分页加载的数量
            .setEnablePlaceholders(false)// 当item为null是否使用PlaceHolder展示
            .setInitialLoadSizeHint(defaultSize)// 预加载的数量
            .build()
    ).build()
}
