package com.yasin.handzap.data

import android.net.Uri
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yasin.handzap.data.entity.Media
import java.util.*

/**
 * Created by Yasin on 25/1/20.
 */
class Converters {

    companion object {
        private const val SEPARATOR = ","
        private val gson = Gson()
    }

    @TypeConverter
    fun UrisToString(uris: List<Uri>?): String? {
        return uris?.map { it }?.joinToString(separator = SEPARATOR)
    }

    @TypeConverter
    fun stringToUriList(uri: String?): List<Uri>? {
        return uri?.split(SEPARATOR)?.map { Uri.parse(it) }
    }

    @TypeConverter
    fun MediaListToString(uris: List<Media>?): String? {
        return gson.toJson(uris)
    }

    @TypeConverter
    fun stringToMediaList(media: String?): List<Media>? {
        if(media== null) return Collections.emptyList()
        return gson.fromJson(media, object: TypeToken<List<Media>>(){}.type)
    }
}