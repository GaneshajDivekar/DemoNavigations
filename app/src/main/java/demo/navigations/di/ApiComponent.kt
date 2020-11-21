package com.demoproject.di

import dagger.Component
import demo.navigations.MainActivity
import demo.navigations.di.ContextModule
import demo.navigations.viewModel.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, ContextModule::class])
interface ApiComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainViewModel: MainViewModel)
}