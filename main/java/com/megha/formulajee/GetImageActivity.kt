package com.megha.formulajee

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_get_image.*
import com.google.firebase.database.DatabaseReference
import android.widget.ProgressBar
import android.support.v7.widget.RecyclerView
//import jdk.nashorn.internal.runtime.ECMAErrors.getMessage
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase





class GetImageActivity : AppCompatActivity() {
//
    var databaseref:DatabaseReference?=null
  lateinit  var uploadslist:MutableList<Upload>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_image)

        imagetoolbar.setNavigationIcon (R.drawable.ic_back_arrow)
    imagetoolbar.setNavigationOnClickListener { onBackPressed() }

     //   recyclerview.hasFixedSize(true)
        recyclerview.hasFixedSize()

        recyclerview.layoutManager = LinearLayoutManager(this)

      uploadslist= mutableListOf()
        databaseref=FirebaseDatabase.getInstance().getReference("uploads")

        databaseref!!.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            Toast.makeText(this@GetImageActivity,"${p0.message}",Toast.LENGTH_SHORT).show()
                progress_circle.visibility=View.GONE
            }

            override fun onDataChange(p0: DataSnapshot) {
for(h in p0.children){
var upload=h.getValue(Upload::class.java)
    uploadslist.add(upload!!)
}

                val adapter=ImageAdapter(applicationContext,uploadslist)
                recyclerview.adapter=adapter
                progress_circle.visibility=View.GONE
            }

        })
    }
}
