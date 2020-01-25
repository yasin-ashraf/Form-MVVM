package com.yasin.handzap.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.yasin.handzap.data.entity.Form

/**
 * Created by Yasin on 24/1/20.
 */
@Dao
interface FormsDao {

    @Insert(onConflict = REPLACE)
    fun saveForm(form: Form)

    @Query("SELECT * FROM Form")
    fun getAllForms() : LiveData<List<Form>>
}