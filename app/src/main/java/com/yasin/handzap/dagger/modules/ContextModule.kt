package com.yasin.handzap.dagger.modules

import android.content.Context
import com.yasin.handzap.dagger.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Created by Yasin on 24/1/20.
 */
@Module
class ContextModule(private val context: Context) {

    @Provides
    @ApplicationScope
    fun provideContext() : Context {
        return context
    }
}