package com.yasin.handzap.data.entity

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
    val budget : Int?,
    val currency : String?,
    val rate : String?,
    val paymentMethod : String?,
    val startDate : String?,
    val jobTerm : String?,
    val attachedFiles : List<Media>?
)