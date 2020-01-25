package com.yasin.handzap.dagger.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yasin.handzap.DATABASE_NAME
import com.yasin.handzap.dagger.qualifiers.ApplicationContext
import com.yasin.handzap.dagger.scopes.ApplicationScope
import com.yasin.handzap.data.HandzapDatabase
import com.yasin.handzap.data.dao.FormsDao
import dagger.Module
import dagger.Provides

/**
 * Created by Yasin on 24/1/20.
 */
@Module(includes = [ContextModule::class])
class DatabaseModule {

    @Provides
    @ApplicationScope
    fun provideDatabase(context: Context) : HandzapDatabase {
        return Room.databaseBuilder(context,HandzapDatabase::class.java, DATABASE_NAME)
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideFormsDao(handzapDatabase: HandzapDatabase) : FormsDao {
        return handzapDatabase.formsDao()
    }

}