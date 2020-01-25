package com.yasin.handzap.ui.formList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.yasin.handzap.Handzap
import com.yasin.handzap.R
import com.yasin.handzap.ui.ViewState
import javax.inject.Inject

/**
 * Created by Yasin on 24/1/20.
 */
class FormListFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var formsViewModel: FormsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Handzap.getApp(requireContext()).mainComponent?.injectFormListFragment(this)
        super.onCreate(savedInstanceState)
        configureViewModel()
        attachFormListObserver()
    }

    private fun attachFormListObserver() {
        formsViewModel.forms.observe(this, Observer { viewState ->
            when(viewState) {
                is ViewState.Success -> {

                }
                is ViewState.EmptyList -> {

                }
            }
        })
    }

    private fun configureViewModel() {
        formsViewModel = ViewModelProviders.of(requireActivity(),viewModelFactory).get(FormsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_form_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_main,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}