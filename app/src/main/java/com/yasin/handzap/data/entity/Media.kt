package com.yasin.handzap.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Yasin on 28/1/20.
 */
@Entity
data class Media(
    @PrimaryKey
    val uri: String,
    val mimeType: String?
)