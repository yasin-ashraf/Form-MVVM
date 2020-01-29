package com.yasin.handzap.ui.newForm

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.yasin.handzap.EventObserver
import com.yasin.handzap.Handzap
import com.yasin.handzap.R
import com.yasin.handzap.ViewModelFactory
import com.yasin.handzap.data.entity.Media
import com.yasin.handzap.databinding.FragmentCreateNewFormBinding
import com.yasin.handzap.utils.observeEditText
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_create_new_form.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by Yasin on 24/1/20.
 */
class NewFormFragment : Fragment(){

    private val compositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }
    private lateinit var documentsAdapter : DocumentsAdapter
    private lateinit var viewDataBinding: FragmentCreateNewFormBinding
    @Inject lateinit var factory: ViewModelFactory
    @Inject lateinit var picassoVideo : Picasso
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
        documentsAdapter = DocumentsAdapter(picassoVideo,requireContext())
        rv_documents.adapter = documentsAdapter
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
        add_card.setOnClickListener {
            pickMediaFromGallery()
        }
        attachDocumentUploadObserver()
        attachTextWatchers()
    }

    private fun attachDocumentUploadObserver() {
        newFormViewModel.fileUploads.observe(this.viewLifecycleOwner, Observer {
            documentsAdapter.submitList(ArrayList(it))
        })
    }

    private fun attachNewFormEventListener() {
        newFormViewModel.createFormEvent.observe(viewLifecycleOwner, EventObserver {
            activity?.viewModelStore?.clear()
            findNavController().navigateUp()
        })
    }

    private fun attachTextWatchers() {
        addTitleObserver()
        addDescriptionObserver()
        addBudgetObserver()
        addRateObserver()
        addPaymentMethodObserver()
        addDateObserver()
        addJobTermObserver()
    }

    private fun addJobTermObserver() {
        newFormViewModel.jobTermOptions.observe(viewLifecycleOwner, Observer {
            if(it.toString().isNotEmpty()){
                newFormViewModel.jobTermOptionsError.postValue("")
                if(newFormViewModel.title.value.isNullOrEmpty()){
                    newFormViewModel.titleError.postValue(getString(R.string.required))
                }
            }
        })

    }

    private fun addDateObserver() {
        newFormViewModel.formattedDate.observe(viewLifecycleOwner, Observer {
            if(it.toString().isNotEmpty()){
                newFormViewModel.formattedDateError.postValue("")
            }
        })

    }

    private fun addPaymentMethodObserver() {
        newFormViewModel.paymentMethod.observe(viewLifecycleOwner, Observer {
            if(it.toString().isNotEmpty()){
                newFormViewModel.paymentMethodError.postValue("")
                if(newFormViewModel.title.value.isNullOrEmpty()){
                    newFormViewModel.titleError.postValue(getString(R.string.required))
                }
            }
        })

    }

    private fun addRateObserver() {
        newFormViewModel.rateOption.observe(viewLifecycleOwner, Observer {
            if(it.toString().isNotEmpty()){
                newFormViewModel.rateOptionError.postValue("")
                if(newFormViewModel.title.value.isNullOrEmpty()){
                    newFormViewModel.titleError.postValue(getString(R.string.required))
                }
            }
        })

    }

    private fun addBudgetObserver() {
        compositeDisposable.add(
            et_budget.observeEditText()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .filter { text -> text.isNotEmpty() }
                .subscribe ({
                    newFormViewModel.budget.postValue(Integer.valueOf(it))
                    newFormViewModel.budgetError.postValue("")
                }, {
                    Log.e("budget obsv",it.toString())
                    newFormViewModel.budgetError.postValue("invalid")
                })
        )
        newFormViewModel.budget.observe(viewLifecycleOwner, Observer {
            if(it?.toString()?.isNotEmpty() == true){
                if(newFormViewModel.title.value.isNullOrEmpty()){
                    newFormViewModel.titleError.postValue(getString(R.string.required))
                }
            }
        })

    }

    private fun addDescriptionObserver() {
        compositeDisposable.add(
            et_description.observeEditText()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .filter { text -> text.isNotEmpty() }
                .subscribe {
                    newFormViewModel.description.postValue(it)
                    newFormViewModel.descriptionError.postValue("")
                }
        )
        newFormViewModel.description.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()){
                if(newFormViewModel.title.value.isNullOrEmpty()){
                    newFormViewModel.titleError.postValue(getString(R.string.required))
                }
            }
        })
    }

    private fun addTitleObserver() {
        compositeDisposable.add(
            et_title.observeEditText()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribe {
                    newFormViewModel.title.postValue(it)
                    newFormViewModel.titleError.postValue("")
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

    private fun pickMediaFromGallery() {
        if (checkStoragePermissions()) {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
                putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*", "video/*"))
            }
            startActivityForResult(intent, RC_MEDIA_PICKER)
        }
    }

    private fun checkStoragePermissions() : Boolean {
        val permissionStorageWrite = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionStorageWrite != PackageManager.PERMISSION_GRANTED) {
            requestStoragePermission()
            return false
        }
        return true
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_PERMISSIONS)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            REQUEST_PERMISSIONS -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    pickMediaFromGallery()
                } else {
                    Toast.makeText(requireContext(),"Permission denied! Grant Permission to upload media.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == RC_MEDIA_PICKER) {
            val uri = data?.data ?: Uri.parse("No-Uri")
            val mimeType: String? = activity?.contentResolver?.getType(uri)
            val media = Media(uri.toString(),mimeType)
            newFormViewModel.addFileUri(media)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_new_form,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_send){
            if(newFormViewModel.isValid()){
                newFormViewModel.createNewForm()
            }
            return true
        }
        return false
    }

    companion object {
        private const val RC_MEDIA_PICKER: Int = 1002
        private const val REQUEST_PERMISSIONS = 1002
    }


    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }
}