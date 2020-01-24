package com.yasin.handzap.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.yasin.handzap.dagger.qualifiers.ApplicationContext
import com.yasin.handzap.dagger.scopes.ApplicationScope
import javax.inject.Inject

/**
 * Created by Yasin on 24/1/20.
 */
@ApplicationScope
class SessionManager @SuppressLint("CommitPrefEdits")
@Inject constructor(@ApplicationContext context: Context) {
    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        val PRIVATE_MODE = 0
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    val isFirstUse: Boolean
        get() = pref.getBoolean(IS_FIRST_USE, true)


    companion object {

        private const val PREF_NAME = "HandZapYasinSession"
        private const val IS_FIRST_USE = "IsFirstUse"
    }
}
