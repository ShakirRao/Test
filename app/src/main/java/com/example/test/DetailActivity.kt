package com.example.test

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.test.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail)

        val userPref = getSharedPreferences("userPref",Context.MODE_PRIVATE)

//        val id:Int  = intent.getIntExtra("id",0)
//        val userid = intent.getIntExtra("userId",0)
//        val title = intent.getStringExtra("title")
//        val text = intent.getStringExtra("text")
        detailBinding.textView11.text = "ID: "+userPref.getInt("id",0).toString()
        detailBinding.textView12.text = "UserId: "+userPref.getInt("userId",0).toString()
        detailBinding.textView13.text = "Title: "+userPref.getString("title","")
        detailBinding.textView14.text = "Text: "+userPref.getString("text","")
    }

    override fun onResume() {
        super.onResume()
        MainActivity.isDetailActivityRunning = true
    }

    override fun onDestroy() {
        super.onDestroy()
        MainActivity.isDetailActivityRunning = false
    }
}