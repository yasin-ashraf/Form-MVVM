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

    private val refresh : MutableLiveData<String> = MutableLiveData()
    val forms : LiveData<ViewState<List<Form>>> = Transformations.map(formsRepository.getForms()) {
        manageViewState(it)
    }

    private fun manageViewState(it: List<Form>): ViewState<List<Form>>? {
        return if(it.isEmpty()){
            ViewState.EmptyList
        }else {
            ViewState.Success(it)
        }
    }
}