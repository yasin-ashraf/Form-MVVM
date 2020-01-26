package com.yasin.handzap.ui.newForm

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.yasin.handzap.Handzap
import com.yasin.handzap.R
import com.yasin.handzap.ViewModelFactory
import javax.inject.Inject

/**
 * Created by Yasin on 26/1/20.
 */
class JobTermDialogFragment : DialogFragment() {

    @Inject lateinit var factory: ViewModelFactory
    private lateinit var newFormViewModel: NewFormViewModel

    private val jobTermOptions by lazy {
        arrayOf(
            getString(R.string.label_no_preference),
            getString(R.string.label_recurring_job),
            getString(R.string.label_same_day_job),
            getString(R.string.label_multi_day_job)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Handzap.getApp(requireActivity()).mainComponent?.injectJobTermFragment(this)
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_AlertDialog)
        configureViewModel()
    }

    private fun configureViewModel() {
        newFormViewModel = ViewModelProviders.of(requireActivity(),factory).get(NewFormViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = AlertDialog.Builder(requireContext(), R.style.AppTheme_AlertDialog )
        alertDialogBuilder.setTitle(getString(R.string.label_rate))
        alertDialogBuilder.setSingleChoiceItems(jobTermOptions, -1) { _, which ->
            newFormViewModel.jobTermOptions.value = (jobTermOptions[which])
        }
        alertDialogBuilder
            .setCancelable(true)
            .setPositiveButton(getString(R.string.label_select)) { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton(getString(android.R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
        val alertDialog = alertDialogBuilder.create()
        (alertDialog.window)?.setWindowAnimations(R.style.DialogAnimation)
        return alertDialog
    }

}