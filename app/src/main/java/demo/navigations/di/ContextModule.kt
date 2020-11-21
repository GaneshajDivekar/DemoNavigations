package demo.navigations.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(context: Context) {
    private var  mContext:Context?=null

    init {
        mContext=context
    }

    @Provides
    @Singleton
    fun provideContext():Context?
    {
        return mContext
    }
}