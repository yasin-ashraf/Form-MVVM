package com.yasin.handzap.dagger.modules

import com.yasin.handzap.dagger.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Yasin on 24/1/20.
 */
@Module
class ApplicationModule {

    @Provides
    @ApplicationScope
    fun provideExecutor() : Executor {
        return Executors.newSingleThreadExecutor()
    }
}