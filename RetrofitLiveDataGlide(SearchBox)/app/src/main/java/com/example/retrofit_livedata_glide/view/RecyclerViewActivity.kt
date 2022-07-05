package com.example.retrofit_livedata_glide.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_livedata_glide.R
import com.example.retrofit_livedata_glide.adapter.RecyclerViewAdapter
import com.example.retrofit_livedata_glide.model.RecyclerList
import com.example.retrofit_livedata_glide.service.RetroInstance
import com.example.retrofit_livedata_glide.service.RetroService
import com.example.retrofit_livedata_glide.viewmodel.RecyclerActivityViewModel
import com.google.android.material.divider.MaterialDividerItemDecoration.VERTICAL
import kotlinx.android.synthetic.main.activity_recycler_view.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class RecyclerViewActivity : AppCompatActivity() {
    var recyclerViewAdapter=RecyclerViewAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        initRecyclerView()
        createData()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager=LinearLayoutManager(this@RecyclerViewActivity)
            recyclerViewAdapter=RecyclerViewAdapter()
            adapter=recyclerViewAdapter
            val divide=DividerItemDecoration(applicationContext,VERTICAL)
            addItemDecoration(divide)

        }
    }
    fun createData(){

        val viewModel=ViewModelProvider(this).get(RecyclerActivityViewModel::class.java)
        viewModel.getObserverData().observe(this, Observer<RecyclerList> {
            if(it !=null){
                recyclerViewAdapter.setData(it.items)
                recyclerViewAdapter.notifyDataSetChanged()
            }
        })

        searchButton.setOnClickListener {
            if(searchBoxId.text.toString().isEmpty()){
                Toast.makeText(this,"Lütfen Arama Kısımına Bir Şey Giriniz",Toast.LENGTH_SHORT).show()
            }
               viewModel.makeCallApiToString(searchBoxId.text.toString())
           }

    }
}