package com.yasin.handzap.ui.formList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.yasin.handzap.Handzap
import com.yasin.handzap.R
import javax.inject.Inject

/**
 * Created by Yasin on 24/1/20.
 */
class FormListFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var formListViewModel: FormListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Handzap.getApp(requireContext()).mainComponent?.injectFormListFragment(this)
        super.onCreate(savedInstanceState)
        configureViewModel()

    }

    private fun configureViewModel() {
        formListViewModel = ViewModelProviders.of(this,viewModelFactory).get(FormListViewModel::class.java)
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