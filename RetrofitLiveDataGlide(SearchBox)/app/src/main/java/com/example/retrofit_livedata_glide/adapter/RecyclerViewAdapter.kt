package com.example.retrofit_livedata_glide.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_livedata_glide.R
import com.example.retrofit_livedata_glide.model.RecyclerData
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items=ArrayList<RecyclerData>()

    fun setData(data:ArrayList<RecyclerData>){
        this.items=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val inflater=LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvTitle=view.tvTitle
        val tvDesc=view.tvDesc
        val imgTuhm=view.imageThumb

        fun bind(data:RecyclerData){
            tvTitle.text=data.name
            if(!TextUtils.isEmpty(data.description)){
                tvDesc.text=data.description
            }else{
                tvDesc.text="Empty"
            }
            val url=data.owner.avatar_url
            Glide.with(imgTuhm)
                .load(url)
                .circleCrop()
                .placeholder(androidx.appcompat.R.drawable.abc_textfield_default_mtrl_alpha)
                .error(androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha)
                .fallback(com.google.android.material.R.drawable.abc_textfield_default_mtrl_alpha)
                .into(imgTuhm)
        }
    }
}