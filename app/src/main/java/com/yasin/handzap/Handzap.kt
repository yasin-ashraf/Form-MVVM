package com.yasin.handzap

import android.app.Application
import android.content.Context
import com.yasin.handzap.dagger.DaggerMainComponent
import com.yasin.handzap.dagger.MainComponent
import com.yasin.handzap.dagger.modules.ContextModule
import com.yasin.handzap.dagger.modules.DatabaseModule

/**
 * Created by Yasin on 24/1/20.
 */
class Handzap : Application() {

    var mainComponent : MainComponent? = null
    private set

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    companion object {
        fun getApp(context: Context) : Handzap {
            return context.applicationContext as Handzap
        }
    }
}