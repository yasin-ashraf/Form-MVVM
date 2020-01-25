package com.yasin.handzap.ui.formList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yasin.handzap.R
import com.yasin.handzap.data.entity.Form

/**
 * Created by Yasin on 25/1/20.
 */
class FormListAdapter : ListAdapter<Form, FormViewHolder>(FormItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_form_list_item,parent,false)
        return FormViewHolder(view)
    }

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {

    }
}

class FormViewHolder(view : View) : RecyclerView.ViewHolder(view)

class FormItemDiffCallBack : DiffUtil.ItemCallback<Form>(){

    override fun areItemsTheSame(oldItem: Form, newItem: Form): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Form, newItem: Form): Boolean {
        return oldItem == newItem
    }

}
