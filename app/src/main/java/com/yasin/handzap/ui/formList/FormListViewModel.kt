package com.yasin.handzap.ui.formList

import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by Yasin on 24/1/20.
 */
class FormListViewModel @Inject constructor(private val formListRepository : FormListRepository) : ViewModel(){

    fun getForms() : String{
        return formListRepository.getForms()
    }

}