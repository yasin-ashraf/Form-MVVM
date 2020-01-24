package com.yasin.handzap.ui.formList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yasin.handzap.Handzap
import com.yasin.handzap.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class FormListActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var formListViewModel: FormListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Handzap.getApp(this).mainComponent?.injectFormListActivity(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureViewModel()
        init()
    }

    private fun init() {
        tv.text = formListViewModel.getForms()
    }

    private fun configureViewModel() {
        formListViewModel = ViewModelProviders.of(this,viewModelFactory).get(FormListViewModel::class.java)
    }
}
