package com.example.retrofit_livedata_glide.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_livedata_glide.model.RecyclerList
import com.example.retrofit_livedata_glide.service.RetroInstance
import com.example.retrofit_livedata_glide.service.RetroService
import retrofit2.Call
import retrofit2.Response

class RecyclerActivityViewModel :ViewModel() {
    var recyclerListData : MutableLiveData<RecyclerList>

    init {
        recyclerListData= MutableLiveData()

    }

    fun getObserverData(): MutableLiveData<RecyclerList>{
        return recyclerListData
    }
    fun makeCallApiToString(input:String){

        val viewModel= RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call=viewModel.getDataFromApıToString(input)
        call.enqueue(object : retrofit2.Callback<RecyclerList>{
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if(response.isSuccessful){
                    recyclerListData.postValue(response.body())
                }else{
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

        })
    }

}