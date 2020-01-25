package com.yasin.handzap.data.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Yasin on 24/1/20.
 */
@Entity
data class Form(
    @PrimaryKey
    val id : String,
    val title : String?,
    val date : String?,
    val views : Int = 0,
    val description : String?,
    val budget : Double?,
    val currency : String?,
    val rate : String?,
    val paymentMethod : String?,
    val startDate : String?,
    val jobTerm : String?,
    val attachedFiles : List<Uri>?
)