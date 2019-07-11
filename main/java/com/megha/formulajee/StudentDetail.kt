package com.megha.formulajee

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_student_detail.*


class StudentDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        addstudenttoolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        addstudenttoolbar.setNavigationOnClickListener { onBackPressed() }
submit.setOnClickListener {
    savestudent()

}


    }

    private fun savestudent(){
        val name=edittextname.text.toString().trim()
        val batch=edittextbatch.text.toString().trim()
        val schoolname=edittextschoolname.text.toString().trim()
        val fathername=edittextfathername.text.toString().trim()
        val fathercontact=edittextfatherno.text.toString().trim()
        val mothername=edittextmothername.text.toString().trim()
        val mothercontact=edittextmothernum.text.toString().trim()
        val address=edittextaddress.text.toString().trim()
     //  val radiogrp=smsradiogrp.checkedRadioButtonId



//        if((smsradiogrp.checkedRadioButtonId)==2131230905){
//            textsms.text="Mother"
//        }
        if((name.isEmpty())){
edittextname.error="Please enter the Proper Details"
            return
        }
        if(batch.isEmpty()){
            edittextbatch.error="Please enter the Proper Details"
            return
        }
        if((schoolname.isEmpty()) ){
            edittextschoolname.error="Please enter the Proper Details"
            return
        }
        if((address.isEmpty())){
            edittextaddress.error="Please enter the Proper Details"
            return
        }
        if ((fathercontact.length<10)&&(fathercontact.length>10)){
            edittextfatherno.error="Please enter the valid mobile number"
            return
        }

        if((mothercontact.length<10)&&(mothercontact.length<10)){
            edittextmothernum.error="Please enter the valid mobile number"
            return
        }


        val ref=FirebaseDatabase.getInstance().getReference("Students")
        val studentid=ref.push().key

        val student=Student(studentid!!,name,batch,schoolname,fathername,fathercontact,mothername,mothercontact,address)
        ref.child(studentid).setValue(student).addOnCompleteListener {
            Toast.makeText(this,"text added",Toast.LENGTH_SHORT).show()
        }
    }
}
