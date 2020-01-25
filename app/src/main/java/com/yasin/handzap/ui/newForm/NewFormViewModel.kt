package com.yasin.handzap.ui.newForm

import androidx.lifecycle.ViewModel
import com.yasin.handzap.ui.formList.FormsRepository
import javax.inject.Inject

/**
 * Created by Yasin on 25/1/20.
 */
class NewFormViewModel @Inject constructor(private val formsRepository: FormsRepository) : ViewModel() {



    fun createNewForm() {
        formsRepository.createNewForm()
    }
}