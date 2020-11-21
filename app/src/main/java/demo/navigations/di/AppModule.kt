package com.demoproject.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(application: Application) {
    private var mApplication: Application? = null

    init {
        this.mApplication = application
    }

    @Provides
    @Singleton
    fun provideApplication(): Application? {
        return mApplication
    }
}