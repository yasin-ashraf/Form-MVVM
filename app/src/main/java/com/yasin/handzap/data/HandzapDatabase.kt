package com.yasin.handzap.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yasin.handzap.data.dao.FormsDao
import com.yasin.handzap.data.entity.Form

/**
 * Created by Yasin on 24/1/20.
 */
@Database(entities = [Form::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HandzapDatabase : RoomDatabase(){

    abstract fun formsDao() : FormsDao
}