package com.example.test

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var backPressedTime = 0L

    companion object{
        var isDetailActivityRunning = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity","onCreate")


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//            val alertDialog = AlertDialog.Builder(mCtx)
//                    alertDialog.setTitle("ID:")
//                    .setMessage("UserId:")
//                    .setIcon(R.drawable.mini_user)
//                    .create()
////
//        alertDialog.show()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val call = jsonPlaceHolderApi.posts
        call?.enqueue(object : Callback<List<Post?>?> {
             override fun onResponse(call: Call<List<Post?>?>, response: Response<List<Post?>?>) {
                if (!response.isSuccessful) {
                    //binding.textViewResult.setText("Code: " + response.code())
                    return
                }

                val posts = response.body()!!
                 val arr = ArrayList<Post>()
                for (post in posts) {
                    if (post != null) {
                        arr.add(post)
                    }
                }
                 val adapter = MyAdapter(this@MainActivity,arr)
                 binding.recyclerView.adapter = adapter
                 binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)

            }

             override fun onFailure(call: Call<List<Post?>?>, t: Throwable) {
                //binding.textViewResult.setText(t.message)
            }
        })
    }

    //press back again to exit
    override fun onBackPressed() {
        Log.d("MainActivity","onBackPress")
        if( 2000 > System.currentTimeMillis() - backPressedTime){
            super.onBackPressed()
        }
        else{
            Toast.makeText(applicationContext, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}