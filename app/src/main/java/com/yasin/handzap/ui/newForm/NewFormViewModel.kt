package com.yasin.handzap.ui.newForm

import androidx.lifecycle.*
import com.yasin.handzap.CURRENCY_US
import com.yasin.handzap.Event
import com.yasin.handzap.data.entity.Form
import com.yasin.handzap.ui.formList.FormsRepository
import com.yasin.handzap.utils.FormValidator
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by Yasin on 25/1/20.
 */
class NewFormViewModel @Inject constructor(private val formsRepository: FormsRepository,
                                           private val formValidator: FormValidator) : ViewModel() {

    private val REQUIRED : String = "Required"
    private val INVALID_VALUE : String = "invalid value"
    private val INVALID : String = "invalid"
    private val _createFormEvent : MutableLiveData<Event<String>> = MutableLiveData()
    val createFormEvent : LiveData<Event<String>> = _createFormEvent
    val paymentMethod : MutableLiveData<String> = MutableLiveData("")
    val paymentMethodError : MutableLiveData<String> = MutableLiveData("")
    val rateOption : MutableLiveData<String> = MutableLiveData("")
    val rateOptionError : MutableLiveData<String> = MutableLiveData("")
    val jobTermOptions : MutableLiveData<String> = MutableLiveData("")
    val jobTermOptionsError : MutableLiveData<String> = MutableLiveData("")
    val selectedDateLong : MutableLiveData<Long> = MutableLiveData()
    val budget : MutableLiveData<Int> = MutableLiveData()
    val budgetError : MutableLiveData<String> = MutableLiveData()
    val title : MutableLiveData<String> = MutableLiveData("")
    val titleError : MutableLiveData<String> = MutableLiveData("")
    val description : MutableLiveData<String> = MutableLiveData("")
    val descriptionError : MutableLiveData<String> = MutableLiveData("")
    val formattedDateError: MutableLiveData<String> = MutableLiveData("")
    val formattedDate : LiveData<String> = Transformations.map(selectedDateLong) {
        convertLongTimeToFormattedDate(it)
    }

    fun createNewForm() {
        val newForm = Form(
            id = System.currentTimeMillis().toString(),
            title = title.value,
            date = formattedDate.value,
            views = 0,
            description = description.value,
            budget = budget.value,
            currency = CURRENCY_US,
            rate = rateOption.value,
            paymentMethod = paymentMethod.value,
            startDate = formattedDate.value,
            jobTerm = jobTermOptions.value,
            attachedFiles = null
        )
        formsRepository.createNewForm(newForm)
        _createFormEvent.value = Event(newForm.id)
    }

    private fun convertLongTimeToFormattedDate(time: Long): String {
        val calendar: Calendar = Calendar.getInstance()
        val format = SimpleDateFormat("EEEE dd MMM", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        calendar.timeInMillis = time
        return format.format(calendar.time)
    }

    fun isValid(): Boolean {
        var valid = true
        if(!formValidator.validateTitle(title.value.toString())){
            titleError.value = REQUIRED
            valid = false
        }
        if(!formValidator.validateDescription(description.value.toString())){
            descriptionError.value = INVALID
            valid = false
        }
        if(!formValidator.validateBudget(budget.value ?: 0)){
            budgetError.value = INVALID_VALUE
            valid = false
        }
        if(!formValidator.validateDate(formattedDate.value.toString())){
            formattedDateError.value = REQUIRED
            valid = false
        }
        if(!formValidator.validateJobTerm(jobTermOptions.value.toString())){
            jobTermOptionsError.value = REQUIRED
            valid = false
        }
        if(!formValidator.validatePaymentMethod(paymentMethod.value.toString())){
            paymentMethodError.value = REQUIRED
            valid = false
        }
        if(!formValidator.validateRate(rateOption.value.toString())){
            rateOptionError.value = REQUIRED
            valid = false
        }
        return valid
    }
}