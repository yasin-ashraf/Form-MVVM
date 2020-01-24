package com.yasin.handzap.data

import androidx.room.RoomDatabase
import com.yasin.handzap.data.dao.FormsDao

/**
 * Created by Yasin on 24/1/20.
 */
abstract class HandzapDatabase : RoomDatabase(){

    abstract fun formsDao() : FormsDao
}