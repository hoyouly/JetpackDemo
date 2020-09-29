/*
 * Copyright 2020. hi-dhl (Jack Deng)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hoyouly.jetpackdemo

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
@Module
@InstallIn(ApplicationComponent::class)
class PkRepositoryModule {

    //全局复用同一个实例
    @Singleton
    @Provides  //@Provides 其意义是告诉Hilt 提供实例Repository时，需要执行PokemonFactory.makePokemonRepository(api, db)
    fun provideTasksRepository(db: AppDataBase): PkRepository {//需要Repository执行具体的代码注入
        Log.e("hoyouly", "provideTasksRepository 执行")
        return PkFactory.makePokemonRepository(db)
    }

}