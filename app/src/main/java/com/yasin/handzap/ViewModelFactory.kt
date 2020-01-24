package com.yasin.handzap


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.yasin.handzap.dagger.scopes.ApplicationScope

import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Yasin on 24/1/20.
 * If any doubts look here :  https://www.techyourchance.com/dependency-injection-viewmodel-with-dagger-2/
 */

@Suppress("UNCHECKED_CAST")
@ApplicationScope
class ViewModelFactory @Inject
constructor(private val viewModelProviderMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    /**
     * get the corresponding Value (Provider Object) from Dagger created implicit Map by passing
     * corresponding Key(ViewModel Name) and get the corresponding ViewModel.
     * All the ViewModels will not be instantiated at once since we are not injecting the ViewModel but the
     * Dagger provider Objects of corresponding ViewModels
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return (viewModelProviderMap[modelClass] ?: error("")).get() as T
    }
}
