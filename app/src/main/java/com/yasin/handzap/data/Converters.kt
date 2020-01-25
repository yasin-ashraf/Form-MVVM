package com.yasin.handzap.data

import android.net.Uri
import androidx.room.TypeConverter

/**
 * Created by Yasin on 25/1/20.
 */
class Converters {

    companion object {
        private const val SEPARATOR = ","
    }

    @TypeConverter
    fun UrisToString(uris: List<Uri>?): String? {
        return uris?.map { it }?.joinToString(separator = SEPARATOR)
    }

    @TypeConverter
    fun stringToUriList(uri: String?): List<Uri>? {
        return uri?.split(SEPARATOR)?.map { Uri.parse(it) }
    }
}