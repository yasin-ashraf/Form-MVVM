package com.yasin.handzap.ui.newForm

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.yasin.handzap.EventObserver
import com.yasin.handzap.Handzap
import com.yasin.handzap.R
import com.yasin.handzap.ViewModelFactory
import com.yasin.handzap.databinding.FragmentCreateNewFormBinding
import com.yasin.handzap.utils.observeEditText
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_create_new_form.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Yasin on 24/1/20.
 */
class NewFormFragment : Fragment(){

    private val compositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }
    private lateinit var viewDataBinding: FragmentCreateNewFormBinding
    @Inject lateinit var factory: ViewModelFactory
    private lateinit var newFormViewModel: NewFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Handzap.getApp(requireContext()).mainComponent?.injectNewFormFragment(this)
        super.onCreate(savedInstanceState)
        configureViewModel()
    }

    private fun configureViewModel() {
        newFormViewModel = ViewModelProviders.of(requireActivity(), factory).get(NewFormViewModel::class.java   )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentCreateNewFormBinding.inflate(inflater,container,false).apply {
            viewmodel = newFormViewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        init()
    }

    private fun init() {
        attachNewFormEventListener()
        et_payment.setOnClickListener {
            findNavController().navigate(R.id.action_newFormFragment_to_paymentMethodDialogFragment)
        }
        et_rate.setOnClickListener {
            findNavController().navigate(R.id.action_newFormFragment_to_rateDialogFragment)
        }
        et_job_term.setOnClickListener {
            findNavController().navigate(R.id.action_newFormFragment_to_jobTermDialogFragment)
        }
        et_date.setOnClickListener {
            showDatePicker()
        }
        attachTextWatchers()
    }

    private fun attachNewFormEventListener() {
        newFormViewModel.createFormEvent.observe(viewLifecycleOwner, EventObserver {
            resetAllEntries()
            findNavController().navigateUp()
        })
    }

    private fun resetAllEntries() {
        et_title.setText("")
        et_description.setText("")
        et_budget.setText("")
        et_budget.setText("")
        et_rate.setText("")
        et_payment.setText("")
        et_date.setText("")
        et_job_term.setText("")
    }

    private fun attachTextWatchers() {
        addTitleObserver()
        addDescriptionObserver()
        addBudgetObserver()
    }

    private fun addBudgetObserver() {
        compositeDisposable.add(
            et_budget.observeEditText()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .filter { text -> text.isNotEmpty() }
                .subscribe {
                    newFormViewModel.budget.postValue(Integer.valueOf(it))
                    if(tl_budget.error?.isNotEmpty() == true){
                        tl_budget.error = ""
                    }
                }
        )
        newFormViewModel.budget.observe(viewLifecycleOwner, Observer {
            if(it.toString().isNotEmpty()){
                if(newFormViewModel.title.value.isNullOrEmpty()){
                    tl_title.error = getString(R.string.required)
                }
            }
        })

    }

    private fun addDescriptionObserver() {
        compositeDisposable.add(
            et_description.observeEditText()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .filter { text -> text.isNotEmpty() }
                .subscribe {
                    newFormViewModel.description.postValue(it)
                    if(tl_description.error?.isNotEmpty() == true){
                        tl_description.error = ""
                    }
                }
        )
        newFormViewModel.description.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()){
                if(newFormViewModel.title.value.isNullOrEmpty()){
                    tl_title.error = getString(R.string.required)
                }
            }
        })
    }

    private fun addTitleObserver() {
        compositeDisposable.add(
            et_title.observeEditText()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .filter { text -> text.isNotEmpty() }
                .subscribe {
                    newFormViewModel.title.postValue(it)
                    if(tl_title.error?.isNotEmpty() == true){
                        tl_title.error = ""
                    }
                }
        )
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            //set date
            calendar.set(year,month,dayOfMonth)
            newFormViewModel.selectedDateLong.value = calendar.timeInMillis
        }
        val datePicker = DatePickerDialog(
            requireActivity(),
//            R.style.AppTheme_DatePickerTheme,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.datePicker.minDate = calendar.timeInMillis
        datePicker.show()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_new_form,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_send){
            if(isValid()){
                newFormViewModel.createNewForm()
            }
            return true
        }
        return false
    }

    private fun isValid() : Boolean{
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }
}