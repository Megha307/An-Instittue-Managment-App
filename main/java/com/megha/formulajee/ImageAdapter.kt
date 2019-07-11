package com.megha.formulajee

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.widget.TextView
import com.squareup.picasso.Picasso
//import sun.security.krb5.internal.KDCOptions.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class ImageAdapter(private val mContext: Context, private val mUploads: List<Upload>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.imageitem, parent, false)
        return ImageViewHolder(v)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val uploadCurrent = mUploads[position]
        holder.textViewName.text = uploadCurrent.name
        Picasso.with(mContext)
                .load(uploadCurrent.imageurl)
                .placeholder(R.mipmap.formula)
                .fit()
                .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return mUploads.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView = itemView.findViewById(R.id.tv_image_name)
        var imageView: ImageView = itemView.findViewById(R.id.iv_list)

    }
}