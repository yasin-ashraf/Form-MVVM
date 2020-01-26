package com.yasin.handzap.utils

import java.util.regex.Pattern

import javax.inject.Inject

/**
 * Created by Yasin on 26/1/20.
 */
class FormValidator @Inject constructor() {

    private val VALID_BUDGET_CHARACTERS = Pattern.compile("[0-9]*")

    fun validateBudget(budget: Int): Boolean {
        return budget > 0 && budget.toString().matches(VALID_BUDGET_CHARACTERS.pattern().toRegex())
    }

    fun validateTitle(title: String): Boolean {
        return title.isNotEmpty() && title.length < 81
    }

    fun validateDescription(description: String): Boolean {
        return description.isNotEmpty() && description.length < 361
    }

    fun validateRate(rate: String): Boolean = rate.isNotEmpty()

    fun validatePaymentMethod(payment : String): Boolean = payment.isNotEmpty()

    fun validateDate(date: Long): Boolean = date != 0L

    fun validateJobTerm(jobTerm: String): Boolean = jobTerm.isNotEmpty()
}
