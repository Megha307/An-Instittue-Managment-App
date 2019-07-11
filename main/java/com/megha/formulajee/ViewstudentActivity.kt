package com.megha.formulajee

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_viewstudent.*

class ViewstudentActivity : AppCompatActivity() {

    lateinit var ref:DatabaseReference
    lateinit var studentlist:MutableList<Student>
    lateinit var listview:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewstudent)

        viewstudenttoolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        viewstudenttoolbar.setNavigationOnClickListener { onBackPressed() }

         studentlist= mutableListOf()
         ref= FirebaseDatabase.getInstance().getReference("Students")

        ref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
              if(p0.exists()){

                  for(h in p0.children){
                     val student=h.getValue(Student::class.java)
                      studentlist.add(student!!)
                  }

                  val adapter=StudentAdapter(applicationContext,R.layout.student,studentlist)
                  viewlistview.adapter=adapter
              }
            }

        })
    }
}
