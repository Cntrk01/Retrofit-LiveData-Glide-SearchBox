package com.example.retrofit_livedata_glide.model


data class RecyclerList(val items:ArrayList<RecyclerData>)
data class RecyclerData(val name:String,val description:String,val owner: com.example.retrofit_livedata_glide.model.Owner)
data class Owner(val avatar_url:String)
