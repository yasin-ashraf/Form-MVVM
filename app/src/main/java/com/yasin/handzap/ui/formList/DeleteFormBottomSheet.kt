package com.yasin.handzap.ui.formList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yasin.handzap.Handzap
import com.yasin.handzap.R
import kotlinx.android.synthetic.main.fragment_bottom_sheet_delete.*

/**
 * Created by Yasin on 25/1/20.
 */
class DeleteFormBottomSheet : BottomSheetDialogFragment() {

    private lateinit var formsViewModel: FormsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Handzap.getApp(requireActivity()).mainComponent?.injectDeleteFormFragment(this)
        super.onCreate(savedInstanceState)
        configureViewModel()
    }

    private fun configureViewModel() {
        formsViewModel = ViewModelProviders.of(requireActivity()).get(FormsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_delete,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ll_delete.setOnClickListener {
            formsViewModel.deleteForm()
            dismiss()
        }
    }
}