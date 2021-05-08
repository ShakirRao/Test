package com.example.test

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.CustomAlertBinding
import com.example.test.databinding.CustomToastBinding
import com.example.test.databinding.RowBinding

class MyAdapter(
        private val mCtx:Context,
        private val arr:ArrayList<Post>):
        RecyclerView.Adapter<MyAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val layoutInflater = from(parent.context)
        val binding = RowBinding.inflate(layoutInflater)

        //toast
        val toastbinding = CustomToastBinding.inflate(layoutInflater)

        //alertdialog
        val alertBinding = CustomAlertBinding.inflate(layoutInflater)


        return ViewHolder(binding,toastbinding,alertBinding)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        val currentUser = arr[position]
        holder.binding.image.setImageResource(R.drawable.user)
        holder.binding.textView1.text = "ID: " + currentUser.id.toString()
        holder.binding.textView2.text = "UserID: " + currentUser.userId.toString()
        holder.binding.textView3.text = "Title: " + currentUser.title
        holder.binding.textView4.text = "Text: " + currentUser.text
        holder.binding.executePendingBindings()

        //clicklistener

        holder.binding.root.setOnClickListener {v ->
            //Toast.makeText(mCtx, "ID: ${currentUser.id} \nUserId:${currentUser.userId}", Toast.LENGTH_SHORT).show()


//            holder.toastbinding.toasttext1.text = "ID: " + currentUser.id.toString()
//            holder.toastbinding.toasttext2.text = "UserID: " + currentUser.userId.toString()
//            val myToast = Toast(mCtx)
//            myToast.duration = Toast.LENGTH_SHORT
//            myToast.setGravity(Gravity.BOTTOM, 0, 0)
//            myToast.view = holder.toastbinding.llWrapper//setting the view of custom toast layout
//            myToast.show()

            //alert dialog
//            holder.alertBinding.alerttimage.setImageResource(R.drawable.user)
//            holder.alertBinding.alerttext1.text = "ID: " + currentUser.id.toString()
//            holder.alertBinding.alerttext2.text = "UserID: " + currentUser.userId.toString()
//            holder.alertBinding.alertttext3.text = "Title: " + currentUser.title
//            holder.alertBinding.alerttext4.text = "Text: " + currentUser.text
//             val alertDialog = AlertDialog.Builder(mCtx)
//                    .setView(holder.alertBinding.root)
////
//            val dialog = alertDialog.create()
//            dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
//            dialog.show()

            //new activity
            if(MainActivity.isDetailActivityRunning)
            {
                return@setOnClickListener
            }

            //sharedpreferences
            val userPref = v.context.getSharedPreferences("userPref", Context.MODE_PRIVATE)
            val editor = userPref.edit()
            editor.apply {
                putInt("id",currentUser.id)
                putInt("userId",currentUser.userId)
                putString("title",currentUser.title)
                putString("text",currentUser.text)
            }
            editor.apply()
            val intent = Intent(mCtx, DetailActivity::class.java)
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)  //to stop opening multiple activities
//            intent.putExtra("id",currentUser.id)
//            intent.putExtra("userId",currentUser.userId)
//            intent.putExtra("title",currentUser.title)
//            intent.putExtra("text",currentUser.text)
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    inner class ViewHolder(val binding: RowBinding,val toastbinding: CustomToastBinding,val alertBinding: CustomAlertBinding): RecyclerView.ViewHolder(binding.root)

}

//class MyAdapter(var mCtx:Context, var resources:Int, var items:List<Post>):ArrayAdapter<Post>(mCtx,resources,items) {
//
//        private lateinit var binding: RowBinding
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//
//        binding = RowBinding.inflate(LayoutInflater.from(mCtx),parent,false)
//
//        var mItem:Post = items[position]
//        binding.image.setImageDrawable(mCtx.getDrawable(R.drawable.user))
//        binding.textView1.text = "ID: ${mItem.id}"
//        binding.textView2.text = "UserID: ${mItem.userId}"
//        binding.textView3.text = "Title: ${mItem.title}"
//        binding.textView4.text = "Body: ${mItem.text}"
//
//        return binding.root
//    }
//}