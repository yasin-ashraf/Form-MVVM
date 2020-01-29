package com.yasin.handzap.dagger.modules

import android.content.Context
import com.squareup.picasso.Picasso
import com.yasin.handzap.dagger.scopes.ApplicationScope
import com.yasin.handzap.utils.VideoRequestHandler
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Yasin on 24/1/20.
 */
@Module(includes = [ContextModule::class])
class ApplicationModule {

    @Provides
    @ApplicationScope
    fun provideExecutor() : Executor {
        return Executors.newCachedThreadPool()
    }

    @Provides
    @ApplicationScope
    fun providePicassoVideo(context: Context) : Picasso {
        return Picasso.Builder(context)
            .addRequestHandler(VideoRequestHandler())
            .build()
    }
}