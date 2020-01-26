package com.yasin.handzap.ui.newForm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yasin.handzap.ui.formList.FormsRepository
import javax.inject.Inject

/**
 * Created by Yasin on 25/1/20.
 */
class NewFormViewModel @Inject constructor(private val formsRepository: FormsRepository) : ViewModel() {

    val paymentMethod : MutableLiveData<String> = MutableLiveData("")
    val rateOption : MutableLiveData<String> = MutableLiveData("")
    val jobTermOptions : MutableLiveData<String> = MutableLiveData("")

    fun createNewForm() {
        formsRepository.createNewForm()
    }
}