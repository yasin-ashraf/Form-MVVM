package com.yasin.handzap.ui.formList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yasin.handzap.data.dao.FormsDao
import com.yasin.handzap.data.entity.Form
import com.yasin.handzap.ui.ViewState
import java.util.concurrent.Executor
import javax.inject.Inject
import kotlin.random.Random

class FormsRepository @Inject constructor(private val executor: Executor,
                                          private val formsDao: FormsDao){

    fun loadForms() : LiveData<List<Form>> {
        return formsDao.getAllForms()
    }

    fun createNewForm() {
        val newForm = Form(
            id = System.currentTimeMillis().toString(),
            title = "Just a title",
            date = "15 Jan 2020",
            views = Random.nextInt(500),
            description = "Lorum ipsum dolor sit amet...",
            budget = 2000.00,
            currency = "USD",
            rate = "20$ per hour",
            paymentMethod = "Cash",
            startDate = "15 Jan 2020",
            jobTerm = null,
            attachedFiles = null
        )
        executor.execute {
            formsDao.saveForm(form = newForm)
        }
    }

}
