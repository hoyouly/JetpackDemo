package com.hoyouly.jetpackdemo

/**
 * @ Time  :  2020/9/15
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

object Version {
    val kotlin = "1.3.41"
    val navigation = "2.0.0"
    val lifecycle = "2.2.0-alpha01"
    val room = "2.1.0-beta01"
    val work = "2.1.0-alpha02"
    val gson = "2.8.2"
    val glide = "4.9.0"
    val fragment = "1.1.0-alpha09"
    val paging = "2.1.0-alpha01"
    val sweet = "1.5.1"
    val trasn = "4.0.1"
    val lottie = "2.7.0"
    val appcompat = "1.2.0"
    val core_ktx = "1.0.2"
    val constraintlayout = "1.1.3"
    val junit = "4.12"
    val material = "1.1.0-alpha06"
    val runner = "1.1.1"
    val espresso_core = "3.2.0"
}


object DependcyConfig {
    val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
    val coreKtx = "androidx.core:core-ktx:${Version.core_ktx}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Version.constraintlayout}"
    val junit = "junit:junit:${Version.junit}"
    val material = "com.google.android.material:material:${Version.material}"
    val testRunner = "androidx.test:runner:${Version.runner}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Version.espresso_core}"

    val kt_stdlib_jdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    val fragment_ktx = "androidx.fragment:fragment-ktx:${Version.fragment}"

    //Paging
    val paging = "androidx.paging:paging-runtime:${Version.paging}"

    val work = "androidx.work:work-runtime-ktx:${Version.work}"
    val gson = "com.google.code.gson:gson:${Version.gson}"

    val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    val glide_transformations = "jp.wasabeef:glide-transformations:${Version.trasn}"

    // SweetDialog
    val sweetalert = "com.github.f0ris.sweetalert:library:${Version.sweet}"

    // Lottie
    val lottie = "com.airbnb.android:lottie:${Version.lottie}"
}

object DependencyRoom {
    // room
    val runtime = "androidx.room:room-runtime:${Version.room}"
    val ktx = "androidx.room:room-ktx:${Version.room}"
    val compiler = "androidx.room:room-compiler:${Version.room}"
    val testing = "androidx.room:room-testing:${Version.room}"
}


object DependencyNavigation {
    val fragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
    val ui_ktx = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
}

object DependencyLifecycle {
    // liveData
    val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"

    // viewModel
    val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    val extensions = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
}
