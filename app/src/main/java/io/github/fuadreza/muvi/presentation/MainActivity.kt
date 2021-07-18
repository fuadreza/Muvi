package io.github.fuadreza.muvi.presentation

import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.core_android.abstraction.BaseActivity
import io.github.fuadreza.muvi.R
import io.github.fuadreza.muvi.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override fun getViewModelClass(): Class<MainActivityViewModel> =
        MainActivityViewModel::class.java

    override fun getLayoutResourceId(): Int = R.layout.activity_main

    override fun initViews() {
        super.initViews()
        supportActionBar?.hide()
    }
}