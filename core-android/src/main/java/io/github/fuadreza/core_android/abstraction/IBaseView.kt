package io.github.fuadreza.core_android.abstraction

import androidx.annotation.LayoutRes

interface IBaseView {
    @LayoutRes
    fun getLayoutResourceId(): Int
    fun initViews(){}
    fun initObservers(){}
}