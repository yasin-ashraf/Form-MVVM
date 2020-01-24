package com.yasin.handzap.ui.formList

import javax.inject.Inject

class FormListRepository @Inject constructor(){

    fun getForms() : String{
        return "This string is returned from Repo"
    }

}
