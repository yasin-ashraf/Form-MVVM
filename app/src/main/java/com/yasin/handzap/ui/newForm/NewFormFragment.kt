package com.yasin.handzap.ui.newForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.yasin.handzap.Handzap
import com.yasin.handzap.R
import com.yasin.handzap.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_create_new_form.*
import javax.inject.Inject

/**
 * Created by Yasin on 24/1/20.
 */
class NewFormFragment : Fragment(){

    @Inject lateinit var factory: ViewModelFactory
    private lateinit var formViewModel: NewFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Handzap.getApp(requireContext()).mainComponent?.injectNewFormFragment(this)
        super.onCreate(savedInstanceState)
        configureViewModel()
    }

    private fun configureViewModel() {
        formViewModel = ViewModelProviders.of(this, factory).get(NewFormViewModel::class.java   )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_new_form,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_new_form.setOnClickListener {
            formViewModel.createNewForm()
        }
    }
}