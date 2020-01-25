package com.yasin.handzap.ui.formList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yasin.handzap.R
import com.yasin.handzap.data.entity.Form
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.layout_form_list_item.view.*

/**
 * Created by Yasin on 25/1/20.
 */
class FormListAdapter : ListAdapter<Form, FormViewHolder>(FormItemDiffCallBack()) {

    private val optionClick = PublishSubject.create<Pair<String,Int>>()
    val clickObserver = optionClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_form_list_item,parent,false)
        return FormViewHolder(view)
    }

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        val form : Form = currentList[position]
        holder.itemView.tv_title.text = form.title
        holder.itemView.tv_date.text = form.date
        holder.itemView.tv_views.text = String.format("%s views",form.views)
        holder.itemView.tv_job_term.text = form.jobTerm
        holder.itemView.tv_rate.text = form.rate
        holder.itemView.iv_options.setOnClickListener {
            optionClick.onNext(Pair(form.id,holder.adapterPosition))
        }
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
