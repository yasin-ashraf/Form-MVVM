package com.yasin.handzap.ui.newForm

import android.content.Context
import android.database.Cursor
import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.yasin.handzap.R
import com.yasin.handzap.data.entity.Media
import com.yasin.handzap.utils.FileUtils
import com.yasin.handzap.utils.VideoRequestHandler
import io.reactivex.Single


/**
 * Created by Yasin on 28/1/20.
 */
class DocumentsAdapter(private val picassoVideo: Picasso,
                       private val context: Context) : ListAdapter<Media, UriViewHolder>(UriDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UriViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_media_item,parent,false)
        return UriViewHolder(view)
    }

    override fun onBindViewHolder(holder: UriViewHolder, position: Int) {
        val media = currentList[position]
        if(media.mimeType?.contains("image") == true){
            holder.itemView.findViewById<ImageView>(R.id.iv_play).visibility = View.INVISIBLE
            Picasso.get()
                .load(media.uri)
                .fit()
                .centerCrop()
                .into(holder.itemView.findViewById<ImageView>(R.id.iv_doc))
        }else {
            holder.itemView.findViewById<ImageView>(R.id.iv_play).visibility = View.VISIBLE
            picassoVideo
                .load("${VideoRequestHandler.SCHEME_VIDEO}:${FileUtils.getPath(context,Uri.parse(media.uri))}")
                .fit()
                .centerCrop()
                .into(holder.itemView.findViewById(R.id.iv_doc), object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception?) {
                        Log.e("Picasso Video", e.toString())
                    }

                })

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