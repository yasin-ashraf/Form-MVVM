package com.yasin.handzap.dagger

import com.yasin.handzap.dagger.modules.ApplicationModule
import com.yasin.handzap.dagger.modules.ContextModule
import com.yasin.handzap.dagger.modules.DatabaseModule
import com.yasin.handzap.dagger.modules.ViewModelModule
import com.yasin.handzap.dagger.scopes.ApplicationScope
import com.yasin.handzap.ui.formList.FormListActivity
import com.yasin.handzap.ui.formList.FormListFragment
import dagger.Component

/**
 * Created by Yasin on 24/1/20.
 */
@ApplicationScope
@Component(modules = [ContextModule::class,ApplicationModule::class,DatabaseModule::class,ViewModelModule::class])
interface MainComponent {

    fun injectFormListFragment(formListFragment: FormListFragment)
}