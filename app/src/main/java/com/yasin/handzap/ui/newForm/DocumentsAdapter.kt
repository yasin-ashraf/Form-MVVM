package com.yasin.handzap.ui.newForm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yasin.handzap.R
import com.yasin.handzap.data.entity.Media

/**
 * Created by Yasin on 28/1/20.
 */
class DocumentsAdapter : ListAdapter<Media, UriViewHolder>(UriDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UriViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_media_item,parent,false)
        return UriViewHolder(view)
    }

    override fun onBindViewHolder(holder: UriViewHolder, position: Int) {
        val media = currentList[position]
        if(media.mimeType?.contains("image") == true){
            Picasso.get()
                .load(media.uri)
                .fit()
                .into(holder.itemView.findViewById<ImageView>(R.id.iv_doc))
        }
    }
}

class UriViewHolder(view : View) : RecyclerView.ViewHolder(view)

class UriDiffCallBack : DiffUtil.ItemCallback<Media>() {
    override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem.uri == newItem.uri
    }

    override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem == newItem
    }


}