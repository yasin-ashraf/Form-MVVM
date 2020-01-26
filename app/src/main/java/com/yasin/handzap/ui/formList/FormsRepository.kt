package com.yasin.handzap.ui.formList

import androidx.lifecycle.LiveData
import com.yasin.handzap.data.dao.FormsDao
import com.yasin.handzap.data.entity.Form
import java.util.concurrent.Executor
import javax.inject.Inject

class FormsRepository @Inject constructor(private val executor: Executor,
                                          private val formsDao: FormsDao){

    fun loadForms() : LiveData<List<Form>> {
        return formsDao.getAllForms()
    }

    fun createNewForm(form: Form) {
        executor.execute {
            formsDao.saveForm(form = form)
        }
    }

    fun deleteForm (id : String) {
        executor.execute {
            formsDao.deleteFOrm(id)
        }
    }

}
