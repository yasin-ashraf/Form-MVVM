package com.yasin.handzap.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yasin.handzap.ViewModelFactory
import com.yasin.handzap.ui.formList.FormListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Yasin on 24/1/20.
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FormListViewModel::class)
    abstract fun bindFormListViewModel(formListViewModel: FormListViewModel) : ViewModel
}