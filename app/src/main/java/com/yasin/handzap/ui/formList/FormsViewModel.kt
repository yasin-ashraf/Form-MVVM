package com.yasin.handzap.ui.formList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yasin.handzap.data.entity.Form
import com.yasin.handzap.ui.ViewState
import javax.inject.Inject

/**
 * Created by Yasin on 24/1/20.
 */
class FormsViewModel @Inject constructor(private val formsRepository : FormsRepository) : ViewModel(){

    private val forms : LiveData<List<Form>> = formsRepository.loadForms()

    fun getForms() = forms

    fun deleteForm (id : String) {
        formsRepository.deleteForm(id)
    }

}