package com.yasin.handzap.data.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Yasin on 28/1/20.
 */
@Entity
data class Media(
    @PrimaryKey
    val uri: Uri,
    val mimeType: String?
)