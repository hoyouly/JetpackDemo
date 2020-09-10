package com.hoyouly.jetpackdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hoyouly.jetpackdemo.db.data.Shoe
import com.hoyouly.jetpackdemo.db.datasource.CustomPageDataSourceFactory
import com.hoyouly.jetpackdemo.db.repository.ShoeRepository

/**
 * @ Time  :  2020-08-26
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

class ShoeModel constructor(shoeRepository: ShoeRepository) : ViewModel() {
    //brand 是一个不可变变量，类型是 MutableLiveData
    // 创建一种MutableLiveData 类型实例，持有的的是String 类型
    private val brand = MutableLiveData<String>().apply {
        value = ALL
    }

    //shoes 是一个变量，类型是 LiveData<List<Shoe>>
    //    val shoes: LiveData<List<Shoe>> =Transformations.switchMap(brand,Function<String,LiveData<List<Shoe>>>(){
//    val shoes: LiveData<List<Shoe>> = brand.switchMap {
//        //  当brand的值变化时就会触发转化函数
//        if (it == ALL) {
//            shoeRepository.getAllShoes()
//        } else {
//            shoeRepository.getShoesByBrand(it)
//        }
//    }


    val shoes: LiveData<PagedList<Shoe>> = LivePagedListBuilder<Int, Shoe>(
        CustomPageDataSourceFactory(shoeRepository)
        , PagedList.Config.Builder()
            .setPageSize(10)// 分页加载的数量
            .setEnablePlaceholders(false)// 当item为null是否使用PlaceHolder展示
            .setInitialLoadSizeHint(10)//// 预加载的数量
            .build()//
    ).build()


    fun setBrand(brand: String) {
//        this.brand.value = brand
        this.brand.postValue(brand)
        this.brand.map { }
    }

    fun clearBrand() {
        this.brand.value = ALL
    }

    companion object {
        public const val ALL = "所有"
    }
}