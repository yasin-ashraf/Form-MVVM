package com.yasin.handzap.ui.newForm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yasin.handzap.ui.formList.FormsRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by Yasin on 25/1/20.
 */
class NewFormViewModel @Inject constructor(private val formsRepository: FormsRepository) : ViewModel() {

    val paymentMethod : MutableLiveData<String> = MutableLiveData("")
    val rateOption : MutableLiveData<String> = MutableLiveData("")
    val jobTermOptions : MutableLiveData<String> = MutableLiveData("")
    val selectedDateLong : MutableLiveData<Long> = MutableLiveData()
    val budget : MutableLiveData<Int> = MutableLiveData()
    val title : MutableLiveData<String> = MutableLiveData("")
    val description : MutableLiveData<String> = MutableLiveData("")
    val formattedDate : LiveData<String> = Transformations.map(selectedDateLong) {
        convertLongTimeToFormattedDate(it)
    }

    fun createNewForm() {
        formsRepository.createNewForm()
    }

    private fun convertLongTimeToFormattedDate(time: Long): String {
        val calendar: Calendar = Calendar.getInstance()
        val format = SimpleDateFormat("EEEE dd MMM", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        calendar.timeInMillis = time
        return format.format(calendar.time)
    }


}