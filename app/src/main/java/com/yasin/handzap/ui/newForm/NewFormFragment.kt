package com.yasin.handzap.ui.newForm

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.yasin.handzap.Handzap
import com.yasin.handzap.R
import com.yasin.handzap.ViewModelFactory
import com.yasin.handzap.databinding.FragmentCreateNewFormBinding
import javax.inject.Inject

/**
 * Created by Yasin on 24/1/20.
 */
class NewFormFragment : Fragment(){

    private lateinit var viewDataBinding: FragmentCreateNewFormBinding
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
        viewDataBinding = FragmentCreateNewFormBinding.inflate(inflater,container,false).apply {
            viewmodel = formViewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_new_form,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_send){
            validateFields()
            return true
        }
        return false
    }

    private fun validateFields() {
        Toast.makeText(requireContext(),"Clicked Send",Toast.LENGTH_SHORT).show()
    }
}